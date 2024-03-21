package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DebateParticipationFailureCode implements FailureCode {
    /**
     * 404 Not Found
     */
    NOT_PARTICIPATED_DEBATE(HttpStatus.NOT_FOUND,"투표를 먼저 하셔야 합니다"),
    NOT_FOUND_DEBATE(HttpStatus.NOT_FOUND, "토론이 없습니다"),
    /**
     * 409 Conflict
     */
    ALREADY_COMMENTED_DEBATE(HttpStatus.CONFLICT,"이미 작성한 댓글입니다");


    private final HttpStatus httpStatus;
    private final String message;
}
