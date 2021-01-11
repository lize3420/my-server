package com.coderask.server.common.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("coderask.common")
public class CommonProperties {
}
