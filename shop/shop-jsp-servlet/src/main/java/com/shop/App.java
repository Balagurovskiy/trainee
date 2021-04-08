package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({
				"classpath:data-source.xml", 
				"classpath:dao-config.xml"
				})
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	ApplicationContext applicationContext = SpringApplication.run(App.class, args);
    }
}