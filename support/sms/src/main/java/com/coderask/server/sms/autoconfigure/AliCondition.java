package com.coderask.server.sms.autoconfigure;

import com.coderask.server.sms.model.SmsConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@RequiredArgsConstructor
public class AliCondition implements Condition {

    private final SMSProperties smsProperties;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return SmsConstant.PRODIVDER_ALI.equals(smsProperties.getProvider());
    }
}
