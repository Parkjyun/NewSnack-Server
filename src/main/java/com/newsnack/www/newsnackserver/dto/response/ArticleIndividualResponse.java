package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.article.model.LocationCategory;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;

public record ArticleIndividualResponse(Long id, String title, String imageUrl, String summary, String body, String createdAt, SectionCategory sectionCategory,
                                        LocationCategory locationCategory, int heartCount, boolean isLiked) {

    public static ArticleIndividualResponse of(Article article, boolean isLiked) {
        return new ArticleIndividualResponse(article.getId(), article.getTitle(), article.getImageUrl(), article.getSummary(), article.getBody(),
                article.getCreatedAt().toString(), article.getSectionCategory(), article.getLocationCategory(), article.getHeartCount(), isLiked);
    }
}

