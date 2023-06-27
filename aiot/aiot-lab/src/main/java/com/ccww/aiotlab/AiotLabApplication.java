package com.ccww.aiotlab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ccww.aiotlab.mapper")
public class AiotLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiotLabApplication.class, args);
    }

}
