package com.newsnack.www.newsnackserver.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newsnack.www.newsnackserver.common.code.failure.FailureCode;
import com.newsnack.www.newsnackserver.common.code.success.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NewSnackResponse<T> {
    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static NewSnackResponse<?> success(SuccessCode successCode) {
        return new NewSnackResponse<>(successCode.getHttpStatus().value(), successCode.getMessage());
    }

    public static <T>NewSnackResponse<T> success(SuccessCode successCode, T data) {
        return new NewSnackResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), data);
    }

    public static NewSnackResponse<?> error(FailureCode failureCode) {
        return new NewSnackResponse<>(failureCode.getHttpStatus().value(), failureCode.getMessage());
    }



}
