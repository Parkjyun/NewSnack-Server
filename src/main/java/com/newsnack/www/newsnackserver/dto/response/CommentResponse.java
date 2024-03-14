package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.comment.model.Comment;

import java.time.LocalDateTime;

public record CommentResponse (Long id, String writerName, String content, LocalDateTime createdAt, int likeCount, boolean isLikedByMe, boolean isMyComment) {
    public static CommentResponse of(Comment comment, boolean isLikedByMe, boolean isMyComment) {
        return new CommentResponse(comment.getId(), comment.getMember().getName(), comment.getContent(), comment.getCreatedAt(), comment.getHeartCount(), isLikedByMe, isMyComment);
    }
}
