package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{

    private final FailureCode failureCode;

    public AuthException(FailureCode failureCode) {
        super("[AuthException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();
    }
}
