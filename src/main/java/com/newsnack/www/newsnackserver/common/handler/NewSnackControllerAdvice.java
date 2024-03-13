package com.newsnack.www.newsnackserver.common.handler;

import com.newsnack.www.newsnackserver.common.exception.AuthException;
import com.newsnack.www.newsnackserver.common.exception.MemberException;
import com.newsnack.www.newsnackserver.common.exception.NewSnackException;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class NewSnackControllerAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<NewSnackResponse<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("handleMissingServletRequestParameterException() in NewSnackControllerAdvice throw MissingServletRequestParameterException : {}", e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(NewSnackResponse.error(e.getStatusCode(), "파라미터가 누락되었습니다"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NewSnackResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("handleMethodArgumentNotValidException() in NewSnackControllerAdvice throw MethodArgumentNotValidException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(NewSnackResponse.error(HttpStatus.BAD_REQUEST, "요청이 유효하지 않습니다."));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<NewSnackResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.info("handleNoHandlerFoundException() in NewSnackControllerAdvice throw NoHandlerFoundException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(NewSnackResponse.error(HttpStatus.NOT_FOUND, "해당하는 api가 없습니다."));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<NewSnackResponse<?>> handleAuthException(AuthException e) {
        log.info("handleAuthException() in NewSnackControllerAdvice throw AuthException : {}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(NewSnackResponse.error(e.getFailureCode()));
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<NewSnackResponse<?>> handleMemberException(MemberException e) {
        log.info("handleMemberException() in NewSnackControllerAdvice throw MemberException : {}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(NewSnackResponse.error(e.getFailureCode()));
    }
    @ExceptionHandler(NewSnackException.class)
    public ResponseEntity<NewSnackResponse<?>> handleNewSnackException(NewSnackException e) {
        log.info("handleNewSnackException() in NewSnackControllerAdvice throw NewSnackException : {}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(NewSnackResponse.error(e.getFailureCode()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<NewSnackResponse<?>> handleException(Exception e) {
        log.error("handleException() in NewSnackControllerAdvice throw Exception : {} ,{}", e.getClass() ,e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(NewSnackResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"));
    }
}
