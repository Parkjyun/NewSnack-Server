package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class CommentException extends RuntimeException {
    private final FailureCode failureCode;

    public CommentException(FailureCode failureCode) {
        super("[CommentException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();
    }
}
