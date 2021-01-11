package com.coderask.server;

import com.coderask.server.auth.protocol.RegisterByMobileRequest;
import com.coderask.server.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

    }

    @Bean
    public CommandLineRunner run() {
        return (String[] args) -> {
            var req = new RegisterByMobileRequest();
            req.setMobile("13466718118");
            req.setPassword("123456");
            req.setCode("20130701");
            var response = authService.registerByMobile(req);
            Assert.isTrue(response.isSuccess(),"insert user error");
        };
    }
}
