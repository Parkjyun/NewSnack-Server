package com.newsnack.www.newsnackserver.domain.article.repository;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
}
