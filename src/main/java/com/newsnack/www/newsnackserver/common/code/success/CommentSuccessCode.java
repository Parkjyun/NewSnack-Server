package com.newsnack.www.newsnackserver.common.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CommentSuccessCode implements SuccessCode{
    /**
     * 201 Created
     **/
    COMMENT_CREATED(HttpStatus.CREATED, "댓글 작성 성공");

    private final HttpStatus httpStatus;
    private final String message;
}