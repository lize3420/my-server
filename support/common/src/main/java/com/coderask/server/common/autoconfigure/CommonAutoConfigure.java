package com.coderask.server.common.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.verificationcode"})
@EnableConfigurationProperties({CommonProperties.class})
public class CommonAutoConfigure {

    @Autowired
    private CommonProperties verificationCodeProperties;

}
