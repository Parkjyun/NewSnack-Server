package com.newsnack.www.newsnackserver.domain.debate.repository;

import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebateJpaRepository extends JpaRepository<Debate, Long> {
}
