package com.example.helloboot;

import com.example.helloboot.util.Base64Encoder;
import com.example.helloboot.util.Encoder;
import com.example.helloboot.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
//@RequiredArgsConstructor
class HelloBootApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("======================");

        // Text Json -> Object
        // Object -> Text Json

        // controller req json(text) -> object
        // response object -> json(text)

        ObjectMapper objectMaapper = new ObjectMapper();

        // object -> text
        // object mapper get method를 활용한다.

        UserVO userVO = new UserVO();
        userVO.setName("steve");
        userVO.setAge(10);
        userVO.setAddress("패캠");
        userVO.setPhoneNumber("010-1234-4562");
        String text = objectMaapper.writeValueAsString(userVO);
        System.out.println(text);

        // text -> object
        // object mapper 는 default 생성자를 필요로 한다.

        UserVO objectUser = objectMaapper.readValue(text, UserVO.class);
        System.out.println(objectUser);

    }

}
