package com.example.helloboot.advice;

import com.example.helloboot.controller.ExceptionApiController;
import org.hibernate.validator.internal.properties.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

// (basePackages = "com.example.helloBoot.controller") <-- 이걸 붙여줌으로써, 해당 에러 처리에 대한 컨트롤러를 지정해 줄 수 있다.
@RestControllerAdvice(basePackageClasses = ExceptionApiController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {


        BindingResult bindingResult = e.getBindingResult();

        bindingResult.getAllErrors().forEach(error -> {

            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            System.out.println("================");
            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e) {

        e.getConstraintViolations().forEach(error -> {
//            String field = error.getLeafBean().toString();
            String field = error.getPropertyPath().toString(); // 2부에서 계속
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            System.out.println(field);
            System.out.println(message);
            System.out.println(invalidValue);

        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity MissingServletRequestParameterException(MissingServletRequestParameterException e) {

        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue = e.getMessage();

        System.out.println(fieldName);
        System.out.println(fieldType);
        System.out.println(invalidValue);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
