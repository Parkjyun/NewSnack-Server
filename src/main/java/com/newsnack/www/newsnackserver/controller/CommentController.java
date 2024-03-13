package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.annotation.MemberId;
import com.newsnack.www.newsnackserver.common.code.success.CommentSuccessCode;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import com.newsnack.www.newsnackserver.dto.CommentRequest;
import com.newsnack.www.newsnackserver.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

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
