package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.annotation.MemberId;
import com.newsnack.www.newsnackserver.dto.response.ArticleIndividualResponse;
import com.newsnack.www.newsnackserver.dto.response.ArticleResponse;
import com.newsnack.www.newsnackserver.service.ArticleService;
import com.newsnack.www.newsnackserver.common.code.failure.ArticleFailureCode;
import com.newsnack.www.newsnackserver.common.code.success.ArticleSuccessCode;
import com.newsnack.www.newsnackserver.common.exception.NewSnackException;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import com.newsnack.www.newsnackserver.domain.article.model.LocationCategory;
import com.newsnack.www.newsnackserver.domain.article.model.SearchOrder;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public NewSnackResponse<List<ArticleResponse>> getArticles(@RequestParam SearchOrder order,
                                                               @RequestParam("page")Integer page,
                                                               @RequestParam(required = false)SectionCategory sectionCategory,
                                                               @RequestParam(required = false)LocationCategory locationCategory) {
        if (order == null || page < 0 || (sectionCategory != null && locationCategory != null) ) {
            throw new NewSnackException(ArticleFailureCode.INVALID_PARAMETER);
        }
        return NewSnackResponse.success(ArticleSuccessCode.GET_ARTICLES_SUCCESS, articleService.getArticles(order, sectionCategory, locationCategory, page));
    }

    @GetMapping("/{articleId}")
    public NewSnackResponse<ArticleIndividualResponse> getArticle(@PathVariable Long articleId, @MemberId Long memberId) {
        return NewSnackResponse.success(ArticleSuccessCode.GET_ARTICLES_SUCCESS, articleService.getArticle(articleId, memberId));
    }
}
