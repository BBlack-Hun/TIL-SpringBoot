package com.example.helloboot.vo;


import lombok.Data;

import java.util.List;

@Data
public class ErrorResponseVO {
    String statusCode;
    String reuqestUrl;
    String code;
    String message;
    String resultCode;

    List<ErrorVO> errorList;
}
