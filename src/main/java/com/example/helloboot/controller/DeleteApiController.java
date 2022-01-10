package com.example.helloboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable(name = "userId") long id, @RequestParam String account) {
        System.out.println(id);
        System.out.println(account);
        
        // delete -> 리소스 삭제 200 OK
    }

}
