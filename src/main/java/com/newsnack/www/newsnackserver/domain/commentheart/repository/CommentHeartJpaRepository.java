package com.newsnack.www.newsnackserver.domain.commentheart.repository;

import com.newsnack.www.newsnackserver.domain.comment.model.Comment;
import com.newsnack.www.newsnackserver.domain.commentheart.model.CommentHeart;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentHeartJpaRepository extends JpaRepository<CommentHeart, Long> {
    Optional<CommentHeart> findByCommentAndMember(Comment comment, Member member);
}
