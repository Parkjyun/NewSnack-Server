package com.newsnack.www.newsnackserver.domain.article.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SearchOrder {

    RECENT("recent"), POPULAR("popular");

    private final String value;
}
