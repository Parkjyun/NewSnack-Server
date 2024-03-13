package com.newsnack.www.newsnackserver.domain.comment.repository;


import com.newsnack.www.newsnackserver.domain.article.model.SearchOrder;
import com.newsnack.www.newsnackserver.domain.comment.model.Comment;
import com.newsnack.www.newsnackserver.dto.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.member where c.article.id = :articleId order by c.id desc")
    List<Comment> findAllWithMemberByArticleIdOrderByIdDesc(Long articleId);
    @Query("select c from Comment c join fetch c.member where c.article.id = :articleId order by c.heartCount desc, c.id desc")
    List<Comment> findAllWithMemberByArticleIdOrderByHeartCountDesc(Long articleId);
    @Query("select distinct c from Comment c left join fetch c.commentHearts join fetch c.member where c.article.id = :articleId order by c.id desc")
    List<Comment> findAllWithMemberAndCommentHeartByArticleIdOrderByIdDesc(Long articleId);
    @Query("select distinct c from Comment c left join fetch c.commentHearts join fetch c.member where c.article.id = :articleId order by c.heartCount desc, c.id desc")
    List<Comment> findAllWithMemberAndCommentHeartByArticleIdOrderByHeartCountDesc(Long articleId);
}
