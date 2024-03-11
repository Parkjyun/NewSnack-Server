package com.newsnack.www.newsnackserver.common.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthSuccessCode implements SuccessCode{


    /**
     * 200 OK
     **/
    SUCCESS_LOGIN(HttpStatus.OK, "소셜 로그인 성공"),
    SUCCESS_LOGNOUT(HttpStatus.OK, "로그아웃 성공"),
    SUCCESS_WITHDRAW(HttpStatus.OK, "회원 탏퇴 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
