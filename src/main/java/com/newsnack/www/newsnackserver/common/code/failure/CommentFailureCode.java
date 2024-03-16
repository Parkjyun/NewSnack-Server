package com.newsnack.www.newsnackserver.common.code.failure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CommentFailureCode implements FailureCode{

    /**
     * 403 Forbidden
     **/
    DELETE_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "댓글 삭제 권한이 없습니다"),
    UPDATE_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "댓글 수정 권한이 없습니다"),

    /**
     * 404 Not Found
     **/
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다"),
    COMMENT_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글 좋아요가 없습니다."),
    /**
     * 409 Conflict
     **/
    COMMENT_HEART_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 좋아요한 댓글입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
