package com.coderask.server.sms.autoconfigure;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.coderask.server.sms.service.AliCheckCodeService;
import com.coderask.server.sms.service.ICheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.sms"})
@EnableJpaRepositories("com.coderask.server.sms.dao")
@EntityScan("com.coderask.server.sms.entity")
@EnableConfigurationProperties({SMSProperties.class})
public class SMSAutoConfiguration {

    @Autowired
    private SMSProperties smsProperties;

    @Bean
//    @ConditionalOnProperty(name = "coderask.sms.provider", havingValue = "aliyun",matchIfMissing = true)
    @Conditional(AliCondition.class)
    public IAcsClient acsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getAliRegionId(), smsProperties.getAccessKey(), smsProperties.getAccessSecret());
        return new DefaultAcsClient(profile);
    }

    @Bean("checkCodeService")
//    @ConditionalOnProperty(name = "coderask.sms.provider", havingValue = "aliyun",matchIfMissing = true)
    @Conditional(AliCondition.class)
    public ICheckCodeService aliCheckCodeService(){
        return new AliCheckCodeService();
    }

    public

}
