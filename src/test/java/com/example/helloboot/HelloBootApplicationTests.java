package com.example.helloboot;

import com.example.helloboot.util.Base64Encoder;
import com.example.helloboot.util.Encoder;
import com.example.helloboot.vo.OMCarVO;
import com.example.helloboot.vo.OMUserVO;
import com.example.helloboot.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void OMTest() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        OMUserVO omUserVO = new OMUserVO();
        omUserVO.setName("홍길동");
        omUserVO.setAge(10);


        OMCarVO omCarVO1 = new OMCarVO();
        omCarVO1.setName("K5");
        omCarVO1.setCarNumber("11가 1234");
        omCarVO1.setType("sedan");

        OMCarVO omCarVO2 = new OMCarVO();
        omCarVO2.setName("스타렉스");
        omCarVO2.setCarNumber("11가 1299");
        omCarVO2.setType("SUV");

        List<OMCarVO> carList = Arrays.asList(omCarVO1, omCarVO2);
        omUserVO.setCars(carList);

        System.out.println(omUserVO);

        String json = objectMapper.writeValueAsString(omUserVO);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

//        String _list = jsonNode.get("cars").asText();
//        System.out.println(_list);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        List<OMCarVO> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<OMCarVO>>() {});
        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());

    }

}
