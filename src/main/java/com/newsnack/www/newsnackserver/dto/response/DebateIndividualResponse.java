package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;

import java.time.LocalDateTime;

public record DebateIndividualResponse (long debateId, String title, String content, Boolean vote, int agreeCount, int disagreeCount, long articleId, String imageUrl,
                                        String articleTitle, SectionCategory sectionCategory, String articleCreatedAt) {
    public static DebateIndividualResponse of(Debate debate, Boolean vote) {
        return new DebateIndividualResponse(debate.getId(), debate.getTitle(), debate.getContent(), vote, debate.getUpVoteCount(), debate.getDownVoteCount(),
                debate.getArticle().getId(), debate.getArticle().getImageUrl(), debate.getArticle().getTitle(), debate.getArticle().getSectionCategory(),
                debate.getArticle().getCreatedAt().toString());
    }
}
