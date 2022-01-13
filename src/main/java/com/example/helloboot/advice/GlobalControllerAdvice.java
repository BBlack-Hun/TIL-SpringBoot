package com.example.helloboot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// (basePackages = "com.example.helloBoot.controller") <-- 이걸 붙여줌으로써, 해당 에러 처리에 대한 컨트롤러를 지정해 줄 수 있다.
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println("===============================");
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
