package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MemberFailureCode implements FailureCode {
    /**
     * 404 NOT FOUND
     */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "가입된 멤버가 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
