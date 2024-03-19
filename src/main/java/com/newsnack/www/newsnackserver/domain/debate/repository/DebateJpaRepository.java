package com.newsnack.www.newsnackserver.domain.debate.repository;

import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DebateJpaRepository extends JpaRepository<Debate, Long> {
    @Query(value = "SELECT d FROM Debate d join fetch d.article WHERE d.id = (SELECT MAX(d.id) FROM Debate d)")
    Optional<Debate> findLatestDebateWithArticleJPQL();

    @Query(value = "SELECT d FROM Debate d join fetch d.article WHERE d.id = :debateId")
    Optional<Debate> findDebateWithArticleJPQL(Long debateId);

    @Query(value = "SELECT d FROM Debate d join fetch d.article ORDER BY d.createdAt DESC")
    List<Debate> findAllDebateWithArticleOrderByCreatedAtDescJPQL();
}
