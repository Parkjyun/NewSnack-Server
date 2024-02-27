package com.newsnack.www.newsnackserver.domain.articleheart.repository;

import com.newsnack.www.newsnackserver.domain.articleheart.model.ArticleHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleHeartJpaRepository extends JpaRepository<ArticleHeart, Long> {
}
