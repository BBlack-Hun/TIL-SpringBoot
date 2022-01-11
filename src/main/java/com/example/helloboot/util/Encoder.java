package com.example.helloboot.util;

import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder(){
//        this.iEncoder = new Base64Encoder();
        this.iEncoder = new UrlEncoder();
    }
    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
