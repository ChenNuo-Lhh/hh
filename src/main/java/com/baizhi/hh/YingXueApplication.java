package com.baizhi.hh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.baizhi.hh.dao")
public class YingXueApplication {
    public static void main(String[] args) {
        SpringApplication.run(YingXueApplication.class, args);
    }
}
