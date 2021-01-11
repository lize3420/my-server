package com.coderask.server.verificationcode.autoconfigure;

import com.coderask.server.verificationcode.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.verificationcode"})
@EnableConfigurationProperties({VerificationCodeProperties.class})
public class VerificationCodeAutoConfigure {

    @Autowired
    private VerificationCodeProperties verificationCodeProperties;
}
