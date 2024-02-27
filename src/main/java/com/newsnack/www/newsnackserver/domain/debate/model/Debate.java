package com.newsnack.www.newsnackserver.domain.debate.model;

import com.newsnack.www.newsnackserver.domain.article.model.Article;
import com.newsnack.www.newsnackserver.domain.article.model.SectionCategory;
import com.newsnack.www.newsnackserver.domain.commom.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Debate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private SectionCategory sectionCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;


}
