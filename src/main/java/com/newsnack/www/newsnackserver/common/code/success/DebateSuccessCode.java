package com.newsnack.www.newsnackserver.common.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DebateSuccessCode implements SuccessCode{

    /**
     * 200 OK
     **/
    GET_DEBATES_SUCCESS(HttpStatus.OK, "토론 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;

}
