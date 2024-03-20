package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DebateFailureCode implements FailureCode {

    /**
     * 404 Not Found
     */
    DEBATE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 토론입니다."),

    /**
     * 409 Conflict
     */
    ALREADY_VOTED_DEBATE(HttpStatus.NOT_FOUND, "이미 투표햤습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
