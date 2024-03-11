package com.newsnack.www.newsnackserver.common.handler;

import com.newsnack.www.newsnackserver.common.exception.AuthException;
import com.newsnack.www.newsnackserver.common.exception.NewSnackException;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NewSnackControllerAdvice {

    @ExceptionHandler(NewSnackException.class)
    public ResponseEntity<NewSnackResponse<?>> handleNewSnackException(NewSnackException e) {
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(NewSnackResponse.error(e.getFailureCode()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<NewSnackResponse<?>> handleAuthException(AuthException e) {
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(NewSnackResponse.error(e.getFailureCode()));
    }
}
