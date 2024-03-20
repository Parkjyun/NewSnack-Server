package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class DebateParticipationException extends RuntimeException{
    private final FailureCode failureCode;
    public DebateParticipationException(FailureCode failureCode) {
        super("[DebateParticipationException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();
    }

}
