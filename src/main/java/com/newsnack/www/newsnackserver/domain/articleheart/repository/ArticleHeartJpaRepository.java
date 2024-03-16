package com.newsnack.www.newsnackserver.domain.articleheart.repository;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.articleheart.model.ArticleHeart;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleHeartJpaRepository extends JpaRepository<ArticleHeart, Long> {
    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);
    Optional<ArticleHeart> findByArticleAndMember(Article article, Member member);
}
