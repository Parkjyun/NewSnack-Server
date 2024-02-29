package com.newsnack.www.newsnackserver.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    OK(HttpStatus.OK, "성공");

    private final HttpStatus status;
    private final String messsage;

    public int getHttpStatusCode() {
        return status.value();
    }
}
