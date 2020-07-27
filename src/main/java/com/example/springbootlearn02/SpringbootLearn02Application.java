package com.example.springbootlearn02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.springbootlearn02.mapper")
@SpringBootApplication
public class SpringbootLearn02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearn02Application.class, args);
    }

}
