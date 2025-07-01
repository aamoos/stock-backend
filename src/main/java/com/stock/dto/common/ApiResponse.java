package com.stock.dto.common;

import com.stock.error.ErrorCode;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;

    private String message;

    private String code;  // Error code

    private String timestamp; // 추가된 timestamp 필드

    private int status; // 추가된 status 필드

    // 성공적인 응답
    public ApiResponse(T data) {
        this.data = data;
        this.message = "OK";
        this.code = "200";  // 기본 코드
        this.timestamp = LocalDateTime.now().toString();  // Current timestamp
        this.status = 200;  // HTTP status for success
    }

    // 실패한 응답 (ErrorCode 활용)
    public ApiResponse(ErrorCode errorCode) {
        this.data = null;
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.timestamp = LocalDateTime.now().toString();  // Current timestamp
        this.status = 500;  // HTTP status for failure
    }
}
