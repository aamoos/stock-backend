package com.stock.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        ErrorCode code = ex.getErrorCode();
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errorCode", code.getCode());
        body.put("message", code.getMessage());
        body.put("status", code.getHttpStatus().value());

        return new ResponseEntity<>(body, code.getHttpStatus());
    }

    //validation
    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
    public ResponseEntity<?> handleValidationException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errorCode", ErrorCode.VALIDATION_ERROR.getCode());
        body.put("status", ErrorCode.VALIDATION_ERROR.getHttpStatus().value());

        String message = "";

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            message = manve.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        } else if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            message = be.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        }

        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errorCode", code.getCode());
        body.put("message", ex.getMessage());
        body.put("status", code.getHttpStatus().value());

        return new ResponseEntity<>(body, code.getHttpStatus());
    }
}

