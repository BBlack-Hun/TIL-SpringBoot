package com.example.helloboot.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EUserVO {

    @NotEmpty
    @Size(min=1, max=10)
    private String name;

    @Min(1)
    @NotNull
    private int age;

}
