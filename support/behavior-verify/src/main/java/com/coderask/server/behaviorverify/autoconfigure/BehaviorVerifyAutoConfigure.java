package com.coderask.server.behaviorverify.autoconfigure;

import com.coderask.server.behaviorverify.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.behaviorverify"})
@EnableConfigurationProperties({BehaviorVerifyProperties.class})
public class BehaviorVerifyAutoConfigure {

    @Autowired
    private BehaviorVerifyProperties behaviorVerifyProperties;


    @Bean
    public VerifyCodeService verifyCodeService(){
        return new VerifyCodeService();
    }
}
