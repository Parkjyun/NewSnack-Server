package com.newsnack.www.newsnackserver.domain.debateparticipationheart.model;

import com.newsnack.www.newsnackserver.domain.debateparticipation.model.DebateParticipation;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DebateParticipationHeart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debate_participation_id")
    private DebateParticipation debateParticipation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
