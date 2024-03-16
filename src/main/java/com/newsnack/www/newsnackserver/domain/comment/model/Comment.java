package com.newsnack.www.newsnackserver.domain.comment.model;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.commentheart.model.CommentHeart;
import com.newsnack.www.newsnackserver.domain.commom.BaseTimeEntity;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)//orphaneRemoval: commentHearts.remove만 해도 DB에서 삭제됨, cascade, orphanRemoval: 둘다 부모 삭제시 자식 모두 삭제
    private List<CommentHeart> commentHearts = new ArrayList<>();

    private String content;

    private int heartCount;

    @Builder
    public Comment(Article article, Member member, String content) {
        this.article = article;
        this.member = member;
        this.content = content;
    }

    public void increaseHeartCount() {
        this.heartCount++;
    }

    public void decreaseHeartCount() {
        this.heartCount--;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
