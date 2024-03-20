package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.DebateParticipationFailureCode;
import com.newsnack.www.newsnackserver.common.exception.DebateParticipationException;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debate.repository.DebateJpaRepository;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.debateparticipation.repository.DebateParticipationJpaRepository;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DebateParticipationService {
    private final DebateParticipationJpaRepository debateParticipationJpaRepository;
    private final DebateJpaRepository debateJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public void participateDebate(Long debateId, Long memberId, String content) {
        Debate debate = debateJpaRepository.getReferenceById(debateId);
        Member member = memberJpaRepository.getReferenceById(memberId);

        DebateParticipation debateParticipation = debateParticipationJpaRepository.findByDebateAndMember(debate, member)
                .orElseThrow(() -> new DebateParticipationException(DebateParticipationFailureCode.NOT_PARTICIPATED_DEBATE));
        if (debateParticipation.getComment() != null) {
            throw new DebateParticipationException(DebateParticipationFailureCode.ALREADY_COMMENTED_DEBATE);
        }
        debateParticipation.updateComment(content);
    }
}
