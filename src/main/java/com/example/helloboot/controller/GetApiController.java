package com.example.helloboot.controller;

import com.example.helloboot.vo.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path="hello")   // http://lcoalhost:9090/api/get/hello
    public String getHello() {
        return "hello Spring Boot by Get API!";
    }

    @RequestMapping(path="/hi", method= RequestMethod.GET)
    public String hi() {
        return "hi Spring Boot by GET API";
    }

    /**
     * // http://localhost:9090/api/get/path-variable/{spring-boot}
     * // http://localhost:9090/api/get/path-variable/{spring}
     * // http://localhost:9090/api/get/path-variable/{spring}
     * @return "";
     */

    @GetMapping("/path-variable/{name}")
    public String pathVarivable(@PathVariable(name= "name") String pathName) {
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    // http://localhost:9090/api/get/query-param?user=john@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = " + entry.getValue() + "\n");
        });



        return sb.toString();
    }

    @GetMapping(path = "query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name + " " + email + " " + age;
    }

    // 테스트 주석 시도..!

    @GetMapping(path = "query-param03")
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();

    }
}
