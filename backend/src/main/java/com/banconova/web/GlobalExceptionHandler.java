package com.banconova.web;

import com.banconova.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> validation(MethodArgumentNotValidException ex){
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(e->e.getField()+": "+e.getDefaultMessage())
                .orElse("Datos inválidos");
        return ResponseEntity.badRequest().body(ApiResponse.fail(msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> generic(Exception ex){
        return ResponseEntity.badRequest().body(ApiResponse.fail(ex.getMessage()));
    }
}
