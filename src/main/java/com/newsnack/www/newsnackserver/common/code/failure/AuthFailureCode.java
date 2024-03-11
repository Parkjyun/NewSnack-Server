package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthFailureCode implements FailureCode{

    /**
     *400 BAD REQUEST
     */
    INVALID_PROVIDER(HttpStatus.BAD_REQUEST, "존재하지 않는 플랫폼입니다."),
    AUTHORIZATION_CODE_EXPIRED(HttpStatus.BAD_REQUEST, "인가코드가 잘못되었습니다"),

    /**
     *401 UNAUTHORIZED
     */
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다"),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰입니다"),
    EMPTY_JWT(HttpStatus.UNAUTHORIZED, "토큰이 비어있습니다");
    private final HttpStatus httpStatus;
    private final String message;
}
