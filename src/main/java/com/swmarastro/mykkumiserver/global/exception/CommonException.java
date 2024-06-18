package com.swmarastro.mykkumiserver.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CommonException extends RuntimeException{

    private final String errorCodeName;
    private final HttpStatus httpStatus;
    private final String message;
    private final String detail;

    public CommonException(ErrorCode errorCode, String message, String detail) {
        this.errorCodeName = errorCode.getErrorCodeName();
        this.httpStatus = errorCode.getHttpStatus();
        this.message = message;
        this.detail = detail;
    }
}
