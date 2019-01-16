package com.chj.bootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class BootbaseApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootbaseApplication.class);
    }
    public static void main(String[] args) {

        SpringApplication.run(BootbaseApplication.class, args);
    }
}

