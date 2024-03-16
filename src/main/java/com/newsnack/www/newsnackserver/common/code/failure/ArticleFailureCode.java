package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ArticleFailureCode implements FailureCode{

    /**
     * 400 BAD REQUEST
     */
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    /**
     * 404 NOT FOUND
     */
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "뉴스를 찾을 수 없습니다"),
    ARTICLE_HEART_NOT_FOUND(HttpStatus.NOT_FOUND, "기사 좋아요가 없습니다"),

    /**
     * 409 CONFLICT
     */
    ARTICLE_HEART_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 좋아요한 게시물입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
