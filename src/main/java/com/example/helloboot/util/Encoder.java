package com.example.helloboot.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder;

//    public Encoder(){
//        this.iEncoder = new Base64Encoder();
////        this.iEncoder = new UrlEncoder();
//    }
public Encoder( IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
