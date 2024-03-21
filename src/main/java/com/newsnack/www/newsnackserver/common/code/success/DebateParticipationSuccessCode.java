package com.newsnack.www.newsnackserver.common.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DebateParticipationSuccessCode implements SuccessCode{
    /**
     * 200 OK
     **/
    DEBATE_PARTICIPATION_SUCCESS(HttpStatus.OK, "토론 댓글 작성 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
