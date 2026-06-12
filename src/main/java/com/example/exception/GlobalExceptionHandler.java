package com.example.exception;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        Map<String, String> m = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(f -> m.put(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(m);
    }
}