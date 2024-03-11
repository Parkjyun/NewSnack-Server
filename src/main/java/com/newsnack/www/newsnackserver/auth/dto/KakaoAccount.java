package com.newsnack.www.newsnackserver.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAccount(@JsonProperty("profile") KakaoUserprofile kakaoUserProfile, String email) {
}
