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
    GET_ARTICLES_SUCCESS(HttpStatus.OK, "기사 조회에 성공했습니다"),
    /**
     * 201 Created
     **/
    ARTICLE_LIKE_SUCCESS(HttpStatus.OK, "기사 좋아요 성공"),
    /**
     * 204 No Content
     **/
    ARTICLE_LIKE_CANCEL_SUCCESS(HttpStatus.OK, "기사 좋아요 취소 성공");

    private final HttpStatus httpStatus;
    private final String message;

}
