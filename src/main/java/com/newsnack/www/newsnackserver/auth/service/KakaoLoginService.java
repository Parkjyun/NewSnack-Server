package com.newsnack.www.newsnackserver.auth.service;

import com.newsnack.www.newsnackserver.auth.dto.KakaoAccessTokenResponse;
import com.newsnack.www.newsnackserver.auth.dto.KakaoUserResponse;
import com.newsnack.www.newsnackserver.auth.dto.SocialInfoDto;
import com.newsnack.www.newsnackserver.auth.client.KakaoAccessFeignClient;
import com.newsnack.www.newsnackserver.auth.client.KakaoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private static final String AUTH_CODE = "authorization_code";
    @Value("${kakao.redirect-url}")
    private String REDIRECT_URL;
    @Value("${kakao.client-id}")
    private String CLIENT_ID;



    private final KakaoFeignClient kakaoFeignClient;
    private final KakaoAccessFeignClient kakaoAccessFeignClient;
    public SocialInfoDto getSocialInfo(String providerToken) {
        KakaoUserResponse kakaoUserResponse = kakaoFeignClient.getUserInformation("Bearer " + providerToken);
        return SocialInfoDto.of(
                kakaoUserResponse.id().toString(),
                kakaoUserResponse.kakaoAccount().email(),
                kakaoUserResponse.kakaoAccount().kakaoUserProfile().nickname());
    }

    public String getKakaoAccessToken (String authorizationCode) {
        KakaoAccessTokenResponse kakaoAccessTokenResponse = kakaoAccessFeignClient.getOAuth2AccessToken(AUTH_CODE, CLIENT_ID, REDIRECT_URL, authorizationCode);
        return kakaoAccessTokenResponse.getAccessToken();
    }
}
