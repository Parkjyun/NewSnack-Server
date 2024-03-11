package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final FailureCode failureCode;

    public MemberException(FailureCode failureCode) {
        super("[MemberException] : " + failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return this.failureCode.getHttpStatus().value();
    }

}
