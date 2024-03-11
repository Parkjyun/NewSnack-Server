package com.newsnack.www.newsnackserver.utils;

import com.newsnack.www.newsnackserver.common.code.failure.AuthFailureCode;
import com.newsnack.www.newsnackserver.common.exception.AuthException;
import com.newsnack.www.newsnackserver.constant.Constants;
import com.newsnack.www.newsnackserver.domain.member.model.MemberRole;
import com.newsnack.www.newsnackserver.dto.response.JwtTokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil implements InitializingBean {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expire-period}")
    private int accessTokenExpirePeriod;

    @Value("${jwt.refresh-token-expire-period}")
    private int refreshTokenExpirePeriod;

    private Key key;


    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);//문자열을 디코딩하여 JWT생성 및 검증에 사용할 수 있도록 바이너리 형태로 바꿈
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenResponse generateTokens(Long id, MemberRole role) {
        return JwtTokenResponse.of(
                generateToken(id, role, accessTokenExpirePeriod),
                generateToken(id, null, refreshTokenExpirePeriod));
    }

    public Claims getTokenBody(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException ex) {
            throw new AuthException(AuthFailureCode.INVALID_JWT);
        } catch (ExpiredJwtException ex) {
            throw new AuthException(AuthFailureCode.EXPIRED_JWT);
        } catch (UnsupportedJwtException ex) {
            throw new AuthException(AuthFailureCode.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException ex) {
            throw new AuthException(AuthFailureCode.EMPTY_JWT);
        }
    }

    private String generateToken(Long id, MemberRole role, Integer expirePeriod) {
        Claims claims = Jwts.claims();
        claims.put(Constants.USER_ID_CLAIM_NAME, id);
        if (role != null)
            claims.put(Constants.USER_ROLE_CLAIM_NAME, role);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirePeriod))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}

