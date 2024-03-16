package com.newsnack.www.newsnackserver.domain.article.model;

import com.newsnack.www.newsnackserver.domain.commom.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private int heartCount;

    public void increaseHeartCount() {
        this.heartCount++;
    }

    public void decreaseHeartCount() {
        this.heartCount--;
    }
}
