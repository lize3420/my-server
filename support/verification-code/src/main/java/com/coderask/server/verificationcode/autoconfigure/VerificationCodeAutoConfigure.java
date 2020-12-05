package com.coderask.server.verificationcode.autoconfigure;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.coderask.server.verificationcode.autoconfigure.VerificationCodeProperties;
import com.coderask.server.verificationcode.service.VerificationCodeService;
import com.coderask.server.verificationcode.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.verificationcode"})
@EnableJpaRepositories("com.coderask.server.verificationcode.dao")
@EntityScan("com.coderask.server.verificationcode.entity")
@EnableConfigurationProperties({VerificationCodeProperties.class})
public class VerificationCodeAutoConfigure {

    @Autowired
    private VerificationCodeProperties smsProperties;

    @Bean
    @ConditionalOnProperty(name = "coderask.sms.provider", havingValue = "aliyun",matchIfMissing = true)
    public IAcsClient acsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getAliRegionId(), smsProperties.getAccessKey(), smsProperties.getAccessSecret());
        return new DefaultAcsClient(profile);
    }

    @Bean("checkCodeService")
    @ConditionalOnProperty(name = "coderask.sms.provider", havingValue = "aliyun",matchIfMissing = true)
    public IVerificationCodeService aliCheckCodeService(){
        return new VerificationCodeService();
    }

}
