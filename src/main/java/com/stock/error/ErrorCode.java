package com.stock.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    VALIDATION_ERROR("400", "요청 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("500", "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}


