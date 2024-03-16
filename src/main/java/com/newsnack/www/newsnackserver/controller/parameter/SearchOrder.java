package com.newsnack.www.newsnackserver.controller.parameter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SearchOrder {

    RECENT("recent"), POPULAR("popular");

    private final String value;
}
