package com.newsnack.www.newsnackserver.domain.debateparticipation.repository;

import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DebateParticipationJpaRepository extends JpaRepository<DebateParticipation, Long> {
    Optional<DebateParticipation> findByDebateAndMember(Debate debate, Member member);

    @Query("select dp from DebateParticipation dp join fetch dp.member where dp.debate.id = :debateId order by dp.id desc")
    List<DebateParticipation> findAllWithMemberByDebateIdOrderByIdDescJPQL(Long debateId);

    @Query("select dp from DebateParticipation dp join fetch dp.member where dp.debate.id = :debateId order by dp.heartCount desc, dp.id desc")
    List<DebateParticipation> findAllWithMemberByDebateIdOrderByHeartCountDescJPQL(Long debateId);

    @Query("select distinct dp from DebateParticipation dp left join fetch dp.debateParticipationHearts join fetch dp.member where dp.debate.id = :debateId order by dp.id desc")
    List<DebateParticipation> findAllWithMemberAndDebateParticipationHeartByDebateIdOrderByIdDescJPQL(Long debateId);
    @Query("select distinct dp from DebateParticipation dp left join fetch dp.debateParticipationHearts join fetch dp.member where dp.debate.id = :debateId order by dp.heartCount desc, dp.id desc")
    List<DebateParticipation> findAllWithMemberAndDebateParticipationHeartByDebateIdOrderByHeartCountDescJPQL(Long debateId);
}
