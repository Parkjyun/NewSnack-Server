package com.newsnack.www.newsnackserver.domain.comment.repository;


import com.newsnack.www.newsnackserver.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
