package com.coderask.server.sms.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties("coderask.sms")
public class SMSProperties {

   /**
    * 提供商，如 aliyun
    */
   private String provider;
   private String aliRegionId = "cn-hangzhou";
   private String accessKey;
   private String accessSecret;
   private String signName;
   /**
    * 验证码有效期,单位：分
    */
   private long checkCodeExpiredTime = 10;

   /**
    * 验证码类型与模板的映射。
    */
   private Map<String,String> checkCodeTemplate = new HashMap<>();


   public static final String PRODIVDER_ALI = "aliyun";
}
