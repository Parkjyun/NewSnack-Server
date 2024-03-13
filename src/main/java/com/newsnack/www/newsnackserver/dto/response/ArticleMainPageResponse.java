package com.newsnack.www.newsnackserver.dto.response;

import com.newsnack.www.newsnackserver.domain.article.model.Article;

public record ArticleMainPageResponse(Long id, String imageUrl, String title, String summary) {
public static ArticleMainPageResponse from(Article article) {
        return new ArticleMainPageResponse(article.getId(), article.getImageUrl(), article.getTitle(), article.getSummary());
    }

}
