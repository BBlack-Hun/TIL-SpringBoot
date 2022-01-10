package com.example.helloboot.controller;

import com.example.helloboot.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main() {
        return "main.html";

    }

    // ResponseEntity
    @ResponseBody
    @GetMapping("/user")
    public UserVO user() {
        UserVO userVO = new UserVO();
        userVO.setName("john");
        userVO.setAddress("멍청한 캠퍼스");

        return userVO;

    }
}
