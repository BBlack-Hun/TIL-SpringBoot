package com.example.helloboot.controller;

import com.example.helloboot.vo.EUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v3")
public class ExceptionUserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUserApiController.class);

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
}
