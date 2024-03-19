package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.DebateFailureCode;
import com.newsnack.www.newsnackserver.common.exception.DebateException;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debate.repository.DebateJpaRepository;
import com.newsnack.www.newsnackserver.dto.response.DebateMainPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DebateService {
    private final DebateJpaRepository debateJpaRepository;

    public DebateMainPageResponse getMainDebate() {
        Debate debate = debateJpaRepository.findLatestDebateWithArticleJPQL().orElseThrow(() -> new DebateException(DebateFailureCode.DEBATE_NOT_FOUND));
        return DebateMainPageResponse.from(debate);
    }
}