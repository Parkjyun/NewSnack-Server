package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.ArticleFailureCode;
import com.newsnack.www.newsnackserver.common.code.failure.CommentFailureCode;
import com.newsnack.www.newsnackserver.common.code.failure.MemberFailureCode;
import com.newsnack.www.newsnackserver.common.exception.ArticleException;
import com.newsnack.www.newsnackserver.common.exception.CommentException;
import com.newsnack.www.newsnackserver.common.exception.MemberException;
import com.newsnack.www.newsnackserver.common.exception.NewSnackException;
import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.controller.parameter.SearchOrder;
import com.newsnack.www.newsnackserver.domain.article.repository.ArticleRepository;
import com.newsnack.www.newsnackserver.domain.comment.model.Comment;
import com.newsnack.www.newsnackserver.domain.comment.repository.CommentJpaRepository;
import com.newsnack.www.newsnackserver.domain.commentheart.model.CommentHeart;
import com.newsnack.www.newsnackserver.domain.commentheart.repository.CommentHeartJpaRepository;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import com.newsnack.www.newsnackserver.dto.CommentRequest;
import com.newsnack.www.newsnackserver.dto.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final ArticleRepository articleRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final CommentHeartJpaRepository commentHeartJpaRepository;

    @Transactional
    public void createComment(Long articleId, CommentRequest commentRequest, Long memberId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleFailureCode.ARTICLE_NOT_FOUND));
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberFailureCode.MEMBER_NOT_FOUND));
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

    public List<CommentResponse> getComments(Long articleId, Long memberId, SearchOrder order) {
        if(memberId == null) { // 비회원일 경우
            if (order.getValue().equals(SearchOrder.RECENT.getValue()))// 최신순 정렬
                return commentJpaRepository.findAllWithMemberByArticleIdOrderByIdDesc(articleId)
                        .stream().map(comment -> CommentResponse.of(comment, false, false)).toList();
            if (order.getValue().equals(SearchOrder.POPULAR.getValue())) // 인기순 정렬
                return commentJpaRepository.findAllWithMemberByArticleIdOrderByHeartCountDesc(articleId)
                        .stream().map(comment -> CommentResponse.of(comment, false, false)).toList();
        }
        //회원일 경우 1000 -> boolean 값 4개 변경 해야함
        if (order.getValue().equals(SearchOrder.RECENT.getValue())) {// 최신순 정렬
            return commentJpaRepository.findAllWithMemberAndCommentHeartByArticleIdOrderByIdDesc(articleId)
                    .stream().map(comment -> CommentResponse.of(comment, isLikedByMe(comment, memberId), isMyComment(comment, memberId))).toList();
        }
        //인기순
        return commentJpaRepository.findAllWithMemberAndCommentHeartByArticleIdOrderByHeartCountDesc(articleId)
                    .stream().map(comment -> CommentResponse.of(comment, isLikedByMe(comment, memberId), isMyComment(comment, memberId))).toList();

    }

    boolean isLikedByMe(Comment comment, Long memberId) {
        return comment.getCommentHearts().stream().anyMatch(commentHeart -> commentHeart.getMember().getId().equals(memberId));
    }

    boolean isMyComment(Comment comment, Long memberId) {
        return comment.getMember().getId().equals(memberId);
    }

    @Transactional
    public void likeComment(Long commentId, Long memberId) {
        Comment comment = commentJpaRepository.getReferenceById(commentId);
        Member member = memberJpaRepository.getReferenceById(memberId);
        commentHeartJpaRepository.findByCommentAndMember(comment, member).ifPresentOrElse(
                (commentHeart) -> {
                    throw new CommentException(CommentFailureCode.COMMENT_HEART_ALREADY_EXISTS);
                },
                () -> {
                    commentHeartJpaRepository.save(CommentHeart.builder().member(member).comment(comment).build());
                    comment.increaseHeartCount();
                }
        );
    }

    @Transactional
    public void cancelCommentLike(Long commentId, Long memberId) {
        Comment comment = commentJpaRepository.getReferenceById(commentId);
        Member member = memberJpaRepository.getReferenceById(memberId);
        commentHeartJpaRepository.findByCommentAndMember(comment, member).ifPresentOrElse(
                (commentHeart) -> {
                    commentHeartJpaRepository.delete(commentHeart);
                    comment.decreaseHeartCount();
                },
                () -> {
                    throw new CommentException(CommentFailureCode.COMMENT_LIKE_NOT_FOUND);
                }
        );
    }
}
