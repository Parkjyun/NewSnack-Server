package com.newsnack.www.newsnackserver.domain.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {

    KAKAO("KAKAO");

    private final String name;
}
