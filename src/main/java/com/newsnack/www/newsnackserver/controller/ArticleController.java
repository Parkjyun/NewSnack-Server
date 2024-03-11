package com.newsnack.www.newsnackserver.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
