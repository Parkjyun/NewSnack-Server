package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class DebateException extends RuntimeException {
private final FailureCode failureCode;

    public DebateException(FailureCode failureCode) {
        super("[DebateException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();
    }
}
