package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.auth.dto.SocialInfoDto;
import com.newsnack.www.newsnackserver.auth.service.KakaoLoginService;
import com.newsnack.www.newsnackserver.dto.request.LoginRequest;
import com.newsnack.www.newsnackserver.common.code.failure.AuthFailureCode;
import com.newsnack.www.newsnackserver.common.code.failure.MemberFailureCode;
import com.newsnack.www.newsnackserver.common.exception.AuthException;
import com.newsnack.www.newsnackserver.common.exception.MemberException;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.model.MemberRole;
import com.newsnack.www.newsnackserver.domain.member.model.MemberStatus;
import com.newsnack.www.newsnackserver.domain.member.model.Provider;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import com.newsnack.www.newsnackserver.dto.response.JwtTokenResponse;
import com.newsnack.www.newsnackserver.utils.JwtUtil;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtUtil jwtUtil;
    private final MemberJpaRepository memberJpaRepository;
    private final KakaoLoginService kakaoLoginService;

    @Transactional
    public JwtTokenResponse login(String authorizationCode, LoginRequest request) {
        String accessToken;
        try {
            accessToken = kakaoLoginService.getKakaoAccessToken(authorizationCode);
        } catch (FeignException e) {
            throw new AuthException(AuthFailureCode.AUTHORIZATION_CODE_EXPIRED);
        }
        SocialInfoDto socialInfo = getSocialInfo(request, accessToken);
        Member member = loadOrCreateMember(request.provider(), socialInfo);
        return generateTokensWithUpdateRefreshToken(member);
    }

    private SocialInfoDto getSocialInfo(LoginRequest request, String providerToken){
        if (request.provider().toString().equals(Provider.KAKAO.toString())){
            return kakaoLoginService.getSocialInfo(providerToken);
        } else {
            throw new AuthException(AuthFailureCode.INVALID_PROVIDER);
        }
    }

    private Member loadOrCreateMember(Provider provider, SocialInfoDto socialInfo){
        boolean isRegistered = memberJpaRepository.existsByProviderAndSerialIdAndMemberStatus(provider, socialInfo.serialId(), MemberStatus.ACTIVE);

        if (!isRegistered){
            Member newMember = Member.builder()
                    .provider(provider)
                    .serialId(socialInfo.serialId())
                    .email(socialInfo.email())
                    .name(socialInfo.name())
                    .role(MemberRole.USER)
                    .memberStatus(MemberStatus.ACTIVE)
                    .build();
            memberJpaRepository.save(newMember);
        }

        return memberJpaRepository.findByProviderAndSerialIdAndMemberStatus(provider, socialInfo.serialId(), MemberStatus.ACTIVE)
                .orElseThrow(() -> new MemberException(MemberFailureCode.MEMBER_NOT_FOUND));
    }

    private JwtTokenResponse generateTokensWithUpdateRefreshToken(Member member){
        JwtTokenResponse jwtTokenResponse = jwtUtil.generateTokens(member.getId(), member.getRole());
        member.updateRefreshToken(jwtTokenResponse.refreshToken());
        return jwtTokenResponse;
    }
}
