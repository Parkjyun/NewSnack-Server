package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.article.model.LocationCategory;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;

import java.time.LocalDateTime;

public record ArticleResponse(Long id, String title, String imageUrl, LocalDateTime createdAt, SectionCategory sectionCategory, LocationCategory locationCategory) {
    public static ArticleResponse of (Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getImageUrl(), article.getCreatedAt(), article.getSectionCategory(), article.getLocationCategory());
    }
}
