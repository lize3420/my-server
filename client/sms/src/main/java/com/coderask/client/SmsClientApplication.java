package com.coderask.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmsClientApplication {

    @Autowired
    private CheckCodeSender checkCodeSender;

    public static void main(String[] args) {
        SpringApplication.run(SmsClientApplication.class, args);

    }

    @Bean
    public CommandLineRunner run() {
        return (String[] args) -> {
            checkCodeSender.sendRegister();
        };
    }
}
