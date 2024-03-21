package com.newsnack.www.newsnackserver.domain.debateparticipation.model;

import com.newsnack.www.newsnackserver.domain.commom.BaseTimeEntity;
import com.newsnack.www.newsnackserver.domain.debate.model.Debate;
import com.newsnack.www.newsnackserver.domain.debateparticipationheart.model.DebateParticipationHeart;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DebateParticipation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debate_id")
    private Debate debate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "debateParticipation", cascade = CascadeType.ALL, orphanRemoval = true)//둘다 부모 삭제시 자식 모두 삭제, orphanRemoval: debateParticipationHearts.remove만 해도 DB에서 삭제됨
    private List<DebateParticipationHeart> debateParticipationHearts = new ArrayList<>();

    private Boolean vote;

    private String comment;

    private int heartCount;

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public DebateParticipation(Debate debate, Member member, Boolean vote) {
        this.debate = debate;
        this.member = member;
        this.vote = vote;
    }

}
