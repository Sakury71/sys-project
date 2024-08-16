package com.sakury;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sakury.mapper")
public class SysProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysProjectApplication.class, args);
    }
}
