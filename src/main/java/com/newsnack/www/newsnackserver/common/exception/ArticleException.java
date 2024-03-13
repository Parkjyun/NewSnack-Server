package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;

public class ArticleException extends RuntimeException{

    private final FailureCode failureCode;

    public ArticleException(FailureCode failureCode) {
        super("[AuthException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();

    }
}
