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
    COMMENT_CREATED(HttpStatus.CREATED, "댓글 작성 성공"),

    /**
     * 204 No Content
     **/
    COMMENT_DELETED_SUCCESS(HttpStatus.NO_CONTENT, "댓글 삭제 성공"),
    COMMENT_UPDATED(HttpStatus.NO_CONTENT, "댓글 수정 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
