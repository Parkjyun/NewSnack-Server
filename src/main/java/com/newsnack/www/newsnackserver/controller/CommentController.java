package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.annotation.MemberId;
import com.newsnack.www.newsnackserver.common.code.success.CommentSuccessCode;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import com.newsnack.www.newsnackserver.controller.parameter.SearchOrder;
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

    @PostMapping("/comments/{commentId}/likes")//comment likecount 갱실 분실 문제 해결하기
    public NewSnackResponse<?> likeComment(@PathVariable Long commentId, @MemberId Long memberId) {
        commentService.likeComment(commentId, memberId);
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_LIKE_SUCCESS);
    }
    @DeleteMapping("/comments/{commentId}/likes")//나중에 comment에 likecount 넣게 되면 -> comment.likecount++-- 하고 동시성 해결하기.
    public NewSnackResponse<?> cancelCommentLike(@PathVariable Long commentId, @MemberId Long memberId) {
        commentService.cancelCommentLike(commentId, memberId);
        return NewSnackResponse.success(CommentSuccessCode.COMMENT_LIKE_CANCEL_SUCCESS);
    }
}
