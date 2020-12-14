package com.coderask.server.auth.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration()
@ComponentScan(basePackages = {"com.coderask.server.auth"})
@EnableJpaRepositories("com.coderask.server.auth.dao")
@EntityScan("com.coderask.server.auth.entity")
@EnableConfigurationProperties({AuthProperties.class})
public class AuthAutoConfiguration {
}
