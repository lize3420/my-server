package com.coderask.server.auth.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("coderask.auth")
public class AuthProperties {
}
