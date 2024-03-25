package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;

import java.time.LocalDateTime;

public record DebateMainPageResponse(long debateId, String title, int agreeCount, int disagreeCount, long articleId, String imageUrl,
                                     String articleTitle, SectionCategory sectionCategory, String articleCreatedAt) {

    public static DebateMainPageResponse from(Debate debate) {
        return new DebateMainPageResponse(debate.getId(), debate.getTitle(), debate.getUpVoteCount(), debate.getDownVoteCount(),
                debate.getArticle().getId(), debate.getArticle().getImageUrl(), debate.getArticle().getTitle(), debate.getArticle().getSectionCategory(),
                debate.getArticle().getCreatedAt().toString());
    }
}
