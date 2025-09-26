package com.asus.tikra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TikraApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikraApplication.class, args);
        System.out.println("http://localhost:8080/api/doc.html#/home");
    }

}
