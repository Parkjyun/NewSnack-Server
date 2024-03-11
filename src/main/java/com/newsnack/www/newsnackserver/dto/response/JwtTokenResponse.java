package com.newsnack.www.newsnackserver.dto.response;

import jakarta.validation.constraints.NotNull;

public record JwtTokenResponse(@NotNull String accessToken, @NotNull String refreshToken) {

    public static JwtTokenResponse of(String accessToken, String refreshToken) {
        return new JwtTokenResponse(accessToken, refreshToken);
    }
    
}
