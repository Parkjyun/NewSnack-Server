package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.DebateFailureCode;
import com.newsnack.www.newsnackserver.common.exception.DebateException;
import com.newsnack.www.newsnackserver.common.exception.DebateParticipationException;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debate.repository.DebateJpaRepository;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.debateparticipation.repository.DebateParticipationJpaRepository;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import com.newsnack.www.newsnackserver.dto.response.DebateIndividualResponse;
import com.newsnack.www.newsnackserver.dto.response.DebateMainPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DebateService {
    private final DebateJpaRepository debateJpaRepository;
    private final DebateParticipationJpaRepository debateParticipationJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public DebateMainPageResponse getMainDebate() {
        Debate debate = debateJpaRepository.findLatestDebateWithArticleJPQL().orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        return DebateMainPageResponse.from(debate);
    }

    public DebateIndividualResponse getDebate(Long debateId, Long memberId) {
        Debate debate = debateJpaRepository.findDebateWithArticleJPQL(debateId).orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        Member member = memberJpaRepository.getReferenceById(memberId);
        if (memberId == null) {//access 없다면
            return DebateIndividualResponse.of(debate, null);
        }
        DebateParticipation debateParticipation;
        try {
            debateParticipation = debateParticipationJpaRepository.findByDebateAndMember(debate, member).get();
        } catch (NoSuchElementException e) {
            return DebateIndividualResponse.of(debate, null);
        }
        return DebateIndividualResponse.of(debate, debateParticipation.getVote());
    }

    public List<DebateMainPageResponse> getDebates() {
        return debateJpaRepository.findAllDebateWithArticleOrderByCreatedAtDescJPQL().stream().map(DebateMainPageResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void voteDebate(Long debateId, Long memberId, boolean vote) {
        Debate debate = debateJpaRepository.findById(debateId).orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        Member member = memberJpaRepository.getReferenceById(memberId);
        debateParticipationJpaRepository.findByDebateAndMember(debate, member).ifPresentOrElse(
                debateParticipation -> {
                    throw new DebateParticipationException(DebateFailureCode.ALREADY_VOTED_DEBATE);
                    },
                () -> debateParticipationJpaRepository.save(new DebateParticipation(debate, member, vote)));
        if (vote) {
            debate.upVote();
        }
        if (!vote) {
            debate.downVote();
        }
    }
}