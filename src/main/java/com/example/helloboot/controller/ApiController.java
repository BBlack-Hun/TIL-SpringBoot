package com.example.helloboot.controller;

import com.example.helloboot.vo.UserVO;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")    // http://localhost:9090/api/hello
    public String hello() {
        return "hello Spring Boot";
    }

    @GetMapping("/text")
    public String txet(@RequestParam String account) {
        return account;
    }

    // JSON
    // req -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public UserVO json(@RequestBody UserVO userVO) {
        return userVO;  // 200 OK
    }

    // ResponseEntity
    @PutMapping("/put2")
    public ResponseEntity<UserVO> put(@RequestBody UserVO userVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO);
    }

}
