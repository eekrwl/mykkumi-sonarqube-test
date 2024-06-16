package com.swmarastro.mykkumiserver.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<ErrorResponse> handle(CommonException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e));
    }
}
