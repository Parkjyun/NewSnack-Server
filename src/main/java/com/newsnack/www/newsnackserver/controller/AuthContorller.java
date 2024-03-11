package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.dto.request.LoginRequest;
import com.newsnack.www.newsnackserver.service.AuthService;
import com.newsnack.www.newsnackserver.constant.Constants;
import com.newsnack.www.newsnackserver.dto.response.JwtTokenResponse;
import com.newsnack.www.newsnackserver.common.code.success.AuthSuccessCode;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthContorller {
    private final AuthService authService;

    @PostMapping("/login")
    public NewSnackResponse<JwtTokenResponse> login(
            @NotNull @RequestHeader(Constants.OAUTH_AUTHORIZATION_CODE) String authorizationCode,
            @Valid @RequestBody LoginRequest request){
        return NewSnackResponse.success(AuthSuccessCode.SUCCESS_LOGIN, authService.login(authorizationCode, request));
    }


}
