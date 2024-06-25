package com.swmarastro.mykkumiserver.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BANNER_NOT_FOUND(HttpStatus.NOT_FOUND),
    ;

    private final HttpStatus httpStatus;

    public String getErrorCodeName() {
        return this.name();
    }

}
