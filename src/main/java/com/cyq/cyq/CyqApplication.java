package com.cyq.cyq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cyq.cyq.mapper")
@SpringBootApplication
public class CyqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyqApplication.class, args);
    }

}
