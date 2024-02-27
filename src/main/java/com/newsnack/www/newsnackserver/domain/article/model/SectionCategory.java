package com.newsnack.www.newsnackserver.domain.article.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SectionCategory {
    ART("art"), ENVIRONMENT("environment"), ECONOMY("economy"), POLITICS("politics"), TECHNOLOGY("technology");

    private final String name;
}
