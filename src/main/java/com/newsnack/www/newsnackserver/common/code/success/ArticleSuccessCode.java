package com.newsnack.www.newsnackserver.common.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ArticleSuccessCode implements SuccessCode{

    /**
     * 200 OK
     **/
    GET_ARTICLES_SUCCESS(HttpStatus.OK, "기사 조회에 성공했습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
