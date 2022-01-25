package com.example.helloboot.vo;

import lombok.Data;

@Data
public class ErrorVO {

    private String field;
    private String message;
    private String invalidValue;
}
