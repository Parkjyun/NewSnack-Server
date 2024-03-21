package com.newsnack.www.newsnackserver.service;

import com.newsnack.www.newsnackserver.common.code.failure.DebateParticipationFailureCode;
import com.newsnack.www.newsnackserver.common.exception.DebateParticipationException;
import com.newsnack.www.newsnackserver.controller.parameter.SearchOrder;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debate.repository.DebateJpaRepository;
import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.debateparticipation.repository.DebateParticipationJpaRepository;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.repository.MemberJpaRepository;
import com.newsnack.www.newsnackserver.dto.response.DebateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<DebateCommentResponse> getDebateComments(Long debateId, Long memberId, SearchOrder searchOrder) {
        Debate debate = debateJpaRepository.findById(debateId).orElseThrow(() -> new DebateParticipationException(DebateParticipationFailureCode.NOT_FOUND_DEBATE));
        if(memberId == null) { // 비회원일 경우
            if (searchOrder.getValue().equals(SearchOrder.RECENT.getValue()))// 최신순 정렬
                return debateParticipationJpaRepository.findAllWithMemberByDebateIdOrderByIdDescJPQL(debateId)
                        .stream().map(debateParticipation -> DebateCommentResponse.of(debateParticipation, false, false)).toList();
            if (searchOrder.getValue().equals(SearchOrder.POPULAR.getValue())) // 인기순 정렬
                return debateParticipationJpaRepository.findAllWithMemberByDebateIdOrderByHeartCountDescJPQL(debateId)
                        .stream().map(debateParticipation -> DebateCommentResponse.of(debateParticipation, false, false)).toList();
        }
        //회원일 경우 1000 -> boolean 값 4개 변경 해야함
        if (searchOrder.getValue().equals(SearchOrder.RECENT.getValue())) {// 최신순 정렬
            return debateParticipationJpaRepository.findAllWithMemberAndDebateParticipationHeartByDebateIdOrderByIdDescJPQL(debateId)
                    .stream().map(debateParticipation -> DebateCommentResponse.of(debateParticipation, isLikedByMe(debateParticipation, memberId), isMyDebateParticipation(debateParticipation, memberId))).toList();
        }
        //인기순
        return debateParticipationJpaRepository.findAllWithMemberAndDebateParticipationHeartByDebateIdOrderByHeartCountDescJPQL(debateId)
                .stream().map(debateParticipation -> DebateCommentResponse.of(debateParticipation, isLikedByMe(debateParticipation, memberId), isMyDebateParticipation(debateParticipation, memberId))).toList();
    }
    private boolean isLikedByMe(DebateParticipation debateParticipation, Long memberId) {
        return debateParticipation.getDebateParticipationHearts().stream().anyMatch(commentHeart -> commentHeart.getMember().getId().equals(memberId));
    }

    private boolean isMyDebateParticipation(DebateParticipation debateParticipation, Long memberId) {
        return debateParticipation.getMember().getId().equals(memberId);
    }

}
