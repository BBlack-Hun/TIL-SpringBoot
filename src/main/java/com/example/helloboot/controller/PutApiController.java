package com.example.helloboot.controller;

import com.example.helloboot.vo.PostRequestVO;
import com.example.helloboot.vo.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put")
    public void put(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
    }

    @PutMapping("/put/{userId}")
    public UserRequest put2(@RequestBody UserRequest userRequest, @PathVariable(name = "userId") Long id) {
        System.out.println(userRequest);
        System.out.println(id);
        return userRequest;
    }
}
