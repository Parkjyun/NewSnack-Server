package com.newsnack.www.newsnackserver.domain.article.repository;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends ArticleJpaRepository {
    @Query("select distinct a from Article a left join fetch a.articleHearts where a.id = :id")
    Optional<Article> findArticleWithArticleHeartsByIdJPQL(Long id);
}
