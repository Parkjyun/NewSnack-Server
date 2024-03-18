package com.newsnack.www.newsnackserver.domain.article.model;

import com.newsnack.www.newsnackserver.domain.articleheart.model.ArticleHeart;
import com.newsnack.www.newsnackserver.domain.commom.BaseTimeEntity;
import com.newsnack.www.newsnackserver.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String body;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private SectionCategory sectionCategory;

    @Enumerated(EnumType.STRING)
    private LocationCategory locationCategory;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleHeart> articleHearts;

    private int heartCount;

    public void increaseHeartCount() {
        this.heartCount++;
    }

    public void decreaseHeartCount() {
        this.heartCount--;
    }

    public boolean isLikedByMember(Member member) {
        for (ArticleHeart articleHeart : articleHearts) {
            if (articleHeart.getMember() == member)//동일한 transaction 내 같은 식별자 갖는 프록시는 == 동일 보장.
                return true;
        }
        return false;
    }
}
