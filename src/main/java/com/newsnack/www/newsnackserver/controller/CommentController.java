package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.annotation.MemberId;
import com.newsnack.www.newsnackserver.common.code.success.CommentSuccessCode;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import com.newsnack.www.newsnackserver.domain.article.model.SearchOrder;
import com.newsnack.www.newsnackserver.dto.CommentRequest;
import com.newsnack.www.newsnackserver.dto.response.CommentResponse;
import com.newsnack.www.newsnackserver.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public NewSnackResponse<List<CommentResponse>> getComments(@PathVariable Long articleId, @MemberId(isForSecuredApi = false) Long memberId,
                                                               @RequestParam SearchOrder order) {
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_GET_SUCCESS, commentService.getComments(articleId, memberId, order));
    }

    @PostMapping("/articles/{articleId}/comments")
    public NewSnackResponse<?> createComment(@PathVariable Long articleId, @RequestBody @Valid CommentRequest commentRequest,
                                             @MemberId Long memberId) {
        commentService.createComment(articleId, commentRequest, memberId);
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_CREATED);
    }

    @PatchMapping("/comments/{commentId}")
    public NewSnackResponse<?> updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentRequest commentRequest,
                                             @MemberId Long memberId) {
        commentService.updateComment(commentId, commentRequest, memberId);
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_UPDATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public NewSnackResponse<?> deleteComment(@PathVariable Long commentId, @MemberId Long memberId) {
        commentService.deleteComment(commentId, memberId);
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_DELETED_SUCCESS);
    }

}
