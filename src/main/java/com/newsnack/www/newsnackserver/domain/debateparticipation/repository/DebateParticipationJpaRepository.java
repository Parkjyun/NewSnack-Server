package com.newsnack.www.newsnackserver.domain.debateparticipation.repository;

import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebateParticipationJpaRepository extends JpaRepository<DebateParticipation, Long> {
    Optional<DebateParticipation> findByDebateIdAndMemberId(Long debateId, Long memberId);
}
