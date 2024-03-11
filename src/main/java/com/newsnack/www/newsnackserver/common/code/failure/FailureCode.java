package com.newsnack.www.newsnackserver.common.code.failure;

import org.springframework.http.HttpStatus;

public interface FailureCode {
    HttpStatus getHttpStatus();
    String getMessage();
}
