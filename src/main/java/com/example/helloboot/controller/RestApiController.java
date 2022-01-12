package com.example.helloboot.controller;

import com.example.helloboot.annotation.Decode;
import com.example.helloboot.annotation.Timer;
import com.example.helloboot.vo.UserTestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        LOGGER.info("get method");
        LOGGER.info("get method : " + id);
        LOGGER.info("get method : " + name);
        return id + " " + name;
    }

    @Timer
    @PostMapping("/post")
    public UserTestVO post(@RequestBody UserTestVO userTestVO) {
        LOGGER.info("post method : "  + userTestVO);
        return userTestVO;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException{
        // db logic
        Thread.sleep(1000* 2);
    }

    @Decode
    @PutMapping("/put")
    public UserTestVO put(@RequestBody
                                      UserTestVO userTestVO){
        LOGGER.info("put");
        LOGGER.info(userTestVO.toString());
        return userTestVO;
    }
}
