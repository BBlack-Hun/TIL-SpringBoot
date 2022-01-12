package com.example.helloboot.vo;

import lombok.Data;

import java.util.List;

@Data
public class OMUserVO {

    private String name;
    private int age;
    private List<OMCarVO> cars;
}
