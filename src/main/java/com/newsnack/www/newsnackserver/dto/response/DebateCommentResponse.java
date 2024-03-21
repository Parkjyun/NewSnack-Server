package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;

import java.time.LocalDateTime;

public record DebateCommentResponse(Long id, String writerName, long memberId, boolean vote, String content, String createdAt, int likeCount, boolean isLikedByMe, boolean isMyComment) {
    public static DebateCommentResponse of(DebateParticipation debateParticipation, boolean isLikedByMe, boolean isMyComment) {
        return new DebateCommentResponse(debateParticipation.getId(), debateParticipation.getMember().getName(), debateParticipation.getMember().getId(),
                debateParticipation.getVote(), debateParticipation.getComment(),
                debateParticipation.getCreatedAt().toString(), debateParticipation.getHeartCount(), isLikedByMe, isMyComment);
    }
}
