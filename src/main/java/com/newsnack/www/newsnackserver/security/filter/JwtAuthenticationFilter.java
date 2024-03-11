package com.newsnack.www.newsnackserver.security.filter;

import com.newsnack.www.newsnackserver.utils.JwtUtil;
import com.newsnack.www.newsnackserver.security.info.UserAuthentication;
import com.newsnack.www.newsnackserver.common.code.failure.AuthFailureCode;
import com.newsnack.www.newsnackserver.common.exception.AuthException;
import com.newsnack.www.newsnackserver.constant.Constants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String token = getJwtFromRequest(request);//AccessToken

        if (StringUtils.hasText(token)) {//Access token이 있다면
            Claims claims = jwtUtil.getTokenBody(token);//claim갖고와서(잘못되면 exception)
            Long userId = claims.get(Constants.USER_ID_CLAIM_NAME, Long.class);//userid갖고
            if (claims.get(Constants.USER_ROLE_CLAIM_NAME, String.class) == null) {//userrole이 없다면 => refresh라면
                if (!request.getRequestURI().equals("/v1/auth/reissue"))//refresh token인데 reissue가 아니라면
                    throw new AuthException(AuthFailureCode.INVALID_JWT);// exception 발
            }
            UserAuthentication authentication = new UserAuthentication(userId, null, null);//사용자 인증 객체 생성
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);//securitycontext holder에 생성한 authentication 저장
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.BEARER_PREFIX)) {
            return bearerToken.substring(Constants.BEARER_PREFIX.length());
        }
        return null;
    }
}
