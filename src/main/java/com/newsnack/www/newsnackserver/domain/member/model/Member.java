package com.newsnack.www.newsnackserver.domain.member.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String refreshToken;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    private LocalDateTime deletedAt;

    @Builder
    public Member(String serialId, String name, String email, Provider provider, MemberRole role, String refreshToken, MemberStatus memberStatus) {
        this.serialId = serialId;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.role = role;
        this.refreshToken = refreshToken;
        this.memberStatus = memberStatus;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
