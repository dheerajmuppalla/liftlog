package com.liftlog.liftlog.exception;


import com.liftlog.liftlog.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ErrorResponse response(Exception ex){
        return ErrorResponse.builder()
                .success(false)
                .status(500)
                .createdAt(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .success(false)
                .status(500)
                .createdAt(LocalDateTime.now())
                .message(ex.getBindingResult().getFieldErrors().toString())
                .build());
    }



}
