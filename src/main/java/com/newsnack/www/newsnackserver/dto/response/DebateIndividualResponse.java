package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;

import java.time.LocalDateTime;

public record DebateIndividualResponse (long debateId, String title, String content, int agreeCount, int disagreeCount, long articleId, String imageUrl,
                                        String articleTitle, SectionCategory sectionCategory, LocalDateTime articleCreatedAt) {
    public static DebateIndividualResponse from(Debate debate) {
        return new DebateIndividualResponse(debate.getId(), debate.getTitle(), debate.getContent(), debate.getUpVoteCount(), debate.getDownVoteCount(),
                debate.getArticle().getId(), debate.getArticle().getImageUrl(), debate.getArticle().getTitle(), debate.getArticle().getSectionCategory(),
                debate.getArticle().getCreatedAt());
    }
}
