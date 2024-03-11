package com.newsnack.www.newsnackserver.domain.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    USER("USER"), ADMIN("ADMIN");

    private final String role;
}
