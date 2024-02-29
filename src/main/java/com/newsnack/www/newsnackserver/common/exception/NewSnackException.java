package com.newsnack.www.newsnackserver.common.exception;

import com.newsnack.www.newsnackserver.common.response.ErrorCode;
import lombok.Getter;

@Getter
public class NewSnackException extends RuntimeException{

    private final ErrorCode errorCode;

    public NewSnackException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getHttpStatusCode() {
        return errorCode.getHttpStatusCode();
    }
}
