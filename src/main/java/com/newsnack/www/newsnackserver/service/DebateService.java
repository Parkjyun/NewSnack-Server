package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.DebateFailureCode;
import com.newsnack.www.newsnackserver.common.exception.DebateException;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debate.repository.DebateJpaRepository;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.debateparticipation.repository.DebateParticipationJpaRepository;
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

    public DebateMainPageResponse getMainDebate() {
        Debate debate = debateJpaRepository.findLatestDebateWithArticleJPQL().orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        return DebateMainPageResponse.from(debate);
    }

    public DebateIndividualResponse getDebate(Long debateId, Long memberId) {//todo: 상세조회에서 투표여부 보내야함
        Debate debate = debateJpaRepository.findDebateWithArticleJPQL(debateId).orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        if (memberId == null) {//access 없다면
            return DebateIndividualResponse.of(debate, null);
        }
        DebateParticipation debateParticipation;
        try {
            debateParticipation = debateParticipationJpaRepository.findByDebateIdAndMemberId(debateId, memberId).get();
        } catch (NoSuchElementException e) {
            return DebateIndividualResponse.of(debate, null);
        }
        return DebateIndividualResponse.of(debate, debateParticipation.getVote());
    }

    public List<DebateMainPageResponse> getDebates() {
        return debateJpaRepository.findAllDebateWithArticleOrderByCreatedAtDescJPQL().stream().map(DebateMainPageResponse::from).collect(Collectors.toList());
    }
}