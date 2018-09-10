package com.tiaotiaopoker;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tiaotiaopoker.service","com.tiaotiaopoker.controller", "com.tiaotiaopoker.config"})
@MapperScan("com.tiaotiaopoker.dao")
@PropertySource({ "classpath:config.properties" })
public class ToutiaoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToutiaoAdminApplication.class, args);
        System.out.print("server start");
    }
}
