package com.example.helloboot.controller;

import com.example.helloboot.vo.VUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ValidationApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationApiController.class);

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody VUserVO user, BindingResult bindingResult) {
        System.out.println(bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                LOGGER.info("field : " + field);
                LOGGER.info(message);


                sb.append("field : " + field.getField());
                sb.append("\n");
                sb.append("message : " + message );

            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }


        // Valid로 VO에서 아래를 검증
//        if (user.getPhoneNumber() == "xxx-xxxx-xxxx") {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }
//        if (user.getAge() >= 90) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }
        
        return ResponseEntity.ok(user);
    }
}

