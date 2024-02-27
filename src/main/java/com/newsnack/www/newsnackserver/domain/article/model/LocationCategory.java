package com.newsnack.www.newsnackserver.domain.article.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LocationCategory {
    EUROPE("europe"), SOUTHAMERICA("south america"), NORTHAMERICA("north america"),
    ASIA("asia"), AFRICA("africa"), OCEANIA("oceania");

    private final String name;
}
