package com.newsnack.www.newsnackserver.domain.debateparticipation.repository;

import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebateParticipationJpaRepository extends JpaRepository<DebateParticipation, Long> {
    Optional<DebateParticipation> findByDebateAndMember(Debate debate, Member member);
}
