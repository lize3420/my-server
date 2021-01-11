package com.coderask.server.verificationcode.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("coderask.verificationcode")
public class VerificationCodeProperties {

   /**
    * 验证码有效期,单位：分
    */
   private int smsExpiredTime = 10;

   private int emailExpiredTime = 30;

   /**
    * 验证码类型与模板的映射。
    */
   private Map<String,String> smsTemplate = new HashMap<>();

   /**
    * 针对万能验证码
    */
   private List<String> magicMobiles = new ArrayList<>();

   /**
    * 万能验证码
    */
   private String magicCode;

}
