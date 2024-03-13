package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.ArticleFailureCode;
import com.newsnack.www.newsnackserver.common.code.failure.CommentFailureCode;
import com.newsnack.www.newsnackserver.common.code.failure.MemberFailureCode;
import com.newsnack.www.newsnackserver.common.exception.NewSnackException;
import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.article.repository.ArticleRepository;
import com.newsnack.www.newsnackserver.domain.comment.model.Comment;
import com.newsnack.www.newsnackserver.domain.comment.repository.CommentJpaRepository;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import com.newsnack.www.newsnackserver.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final ArticleRepository articleRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public void createComment(Long articleId, CommentRequest commentRequest, Long memberId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NewSnackException(ArticleFailureCode.ARTICLE_NOT_FOUND));
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new NewSnackException(MemberFailureCode.MEMBER_NOT_FOUND));
        Comment comment = Comment.builder()
                .article(article)
                .member(member)
                .content(commentRequest.content())
                .build();
        commentJpaRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentJpaRepository.findById(commentId).orElseThrow(() -> new NewSnackException(CommentFailureCode.COMMENT_NOT_FOUND));
        if (!comment.getMember().getId().equals(memberId)) {
            throw new NewSnackException(CommentFailureCode.DELETE_NOT_AUTHORIZED);
        }
        commentJpaRepository.delete(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest commentRequest, Long memberId) {
        Comment comment = commentJpaRepository.findById(commentId).orElseThrow(() -> new NewSnackException(CommentFailureCode.COMMENT_NOT_FOUND));
        if (!comment.getMember().getId().equals(memberId)) {
            throw new NewSnackException(CommentFailureCode.UPDATE_NOT_AUTHORIZED);
        }
        comment.updateContent(commentRequest.content());
    }
}
