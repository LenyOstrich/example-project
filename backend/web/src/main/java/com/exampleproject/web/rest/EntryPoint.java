package com.exampleproject.web.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class EntryPoint {
    public static void main(String[] args) {
        SpringApplication.run(EntryPoint.class, args);
    }
}
