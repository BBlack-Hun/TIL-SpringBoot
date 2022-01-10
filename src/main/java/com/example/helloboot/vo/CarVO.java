package com.example.helloboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarVO {

    private String name;

    @JsonProperty("car_number")
    private String carNumber;
}
