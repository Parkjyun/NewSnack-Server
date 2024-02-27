package com.newsnack.www.newsnackserver.domain.commentheart.repository;

import com.newsnack.www.newsnackserver.domain.commentheart.model.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartJpaRepository extends JpaRepository<CommentHeart, Long> {
}
