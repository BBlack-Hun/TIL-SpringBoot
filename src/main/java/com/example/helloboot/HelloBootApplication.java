package com.example.helloboot;

import com.example.helloboot.util.Base64Encoder;
import com.example.helloboot.util.Encoder;
import com.example.helloboot.util.IEncoder;
import com.example.helloboot.util.UrlEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HelloBootApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloBootApplication.class, args);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base 64 encoding
        IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);
        LOGGER.info(result);

//        // Base 64 encoding or URL encoding
//        Encoder encoder2 = new Encoder();
//        String result2 = encoder2.encode(url);
//        LOGGER.info(result2);

        // override // 파라미터로 넣어준 인코더에 따라 달라짐
        Encoder encoder3 = new Encoder(new UrlEncoder());
        String result3 = encoder3.encode(url);
        LOGGER.info(result3);

        // url encoding
        IEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);
        LOGGER.info(urlResult);

        LOGGER.info("==================================");

        ApplicationContext context = ApplicationContextProvider.getContext();

        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        Encoder encoder4 = new Encoder(base64Encoder);

        // base64Encoder
        String result4 = encoder4.encode(url);

        // urlEncoder
        encoder4.setIEncoder(urlEncoder);
        result4 = encoder4.encode(url);

        LOGGER.info(result4);

        LOGGER.info("==================================");

        Encoder encoder5 = context.getBean("base64Encode", Encoder.class);
        String result5 = encoder5.encode(url);
        LOGGER.info(result5);
    }

}


@Configuration
class AppConfig {

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}