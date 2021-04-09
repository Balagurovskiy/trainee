package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:data-source.xml"})
public class App{

    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
}