package com.newsnack.www.newsnackserver.domain.debateparticipation.repository;

import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebateParticipationJpaRepository extends JpaRepository<DebateParticipation, Long> {
}
