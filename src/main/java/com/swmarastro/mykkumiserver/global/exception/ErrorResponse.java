package com.swmarastro.mykkumiserver.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String detail;

    public static ErrorResponse from(CommonException e) {
        return ErrorResponse.builder()
                .errorCode(e.getErrorCodeName())
                .message(e.getMessage())
                .detail(e.getDetail())
                .build();
    }
}
