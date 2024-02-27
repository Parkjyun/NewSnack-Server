package com.newsnack.www.newsnackserver.domain.member.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberStatus {
    ACTIVE("active"), INACTIVE("inactive");

    private final String status;
}
