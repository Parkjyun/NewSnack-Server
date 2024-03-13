package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.ArticleFailureCode;
import com.newsnack.www.newsnackserver.common.exception.ArticleException;
import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.articleheart.repository.ArticleHeartJpaRepository;
import com.newsnack.www.newsnackserver.dto.response.ArticleIndividualResponse;
import com.newsnack.www.newsnackserver.dto.response.ArticleMainPageResponse;
import com.newsnack.www.newsnackserver.dto.response.ArticleResponse;
import com.newsnack.www.newsnackserver.domain.article.model.LocationCategory;
import com.newsnack.www.newsnackserver.domain.article.model.SearchOrder;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import com.newsnack.www.newsnackserver.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final static int PAGE_SIZE = 9;

    private final ArticleRepository articleRepository;
    private final ArticleHeartJpaRepository articleHeartJpaRepository;

    public List<ArticleResponse> getArticles(SearchOrder order, SectionCategory sectionCategory, LocationCategory locationCategory, Integer page) {

        if(order.equals(SearchOrder.RECENT)) {
            if (sectionCategory != null) {
                return articleRepository.findAllBySectionCategoryOrderByIdDesc(sectionCategory, PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
            }
            if (locationCategory != null) {
                return articleRepository.findAllByLocationCategoryOrderByIdDesc(locationCategory, PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
            }
           return articleRepository.findAllOrderByIdDesc(PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
        }
       if(order.equals(SearchOrder.POPULAR)) {
           if (sectionCategory != null) {
               return articleRepository.findAllBySectionCategoryOrderByHeartCountDescAndIdDesc(sectionCategory, PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
           }
           if (locationCategory != null) {
               return articleRepository.findAllByLocationCategoryOrderByHeartCountDescAndIdDesc(locationCategory, PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
           }
            return articleRepository.findAllOrderByPopularAndIdDesc(PageRequest.of(page, PAGE_SIZE)).getContent().stream().map(ArticleResponse::from).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public ArticleIndividualResponse getArticle(Long articleId, Long memberId) {//현재 로그인 x 기준임
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleFailureCode.ARTICLE_NOT_FOUND));
        if (memberId == null) {
            boolean isLiked = false;
            return ArticleIndividualResponse.of(article, isLiked);
        }
        boolean isLiked = articleHeartJpaRepository.existsByArticleIdAndMemberId(articleId, memberId);
        return ArticleIndividualResponse.of(article, isLiked);
    }

    public List<ArticleMainPageResponse> getMainArticles() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
        return articleRepository.findTop5ByCreatedAtAfterOrderByHeartCountDescIdDesc(oneWeekAgo).stream().map(ArticleMainPageResponse::from).collect(Collectors.toList());
    }
}
