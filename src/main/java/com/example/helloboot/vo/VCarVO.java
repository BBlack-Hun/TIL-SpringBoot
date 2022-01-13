package com.example.helloboot.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VCarVO {

    @NotBlank
    private String name;

    @NotBlank
    private String carNumber;

    @NotBlank
    private String type;
}
