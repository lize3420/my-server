package com.coderask.server.auth;

import com.coderask.server.auth.protocol.RegisterByMobileRequest;
import com.coderask.server.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Autowired
	private SecurityService securityService;

	@Bean
	public CommandLineRunner testUser(){
		return args -> {
			RegisterByMobileRequest req = new RegisterByMobileRequest();
			req.setMobile("13466718118");
			req.setPassword("123456");
			securityService.registerByMobile(req);
		};
	}

}
