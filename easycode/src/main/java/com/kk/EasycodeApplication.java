package com.kk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kk.dao")
@SpringBootApplication
public class EasycodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasycodeApplication.class, args);
    }

}
