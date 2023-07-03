package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MainRunner {
    public static void main(String[] args){
        SpringApplication.run(MainRunner.class, args);
    }
}
