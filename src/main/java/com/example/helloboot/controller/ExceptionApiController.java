package com.example.helloboot.controller;

import com.example.helloboot.vo.EUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2")
public class ExceptionApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionApiController.class);

    @GetMapping("user") // ?name=1234&age=10
    public EUserVO get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {

        EUserVO user = new EUserVO();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;
        return user;
    }

    @PostMapping("")
    public EUserVO post(@Valid @RequestBody EUserVO user) {
        LOGGER.info(user.toString());

        return user;

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("API controller");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
