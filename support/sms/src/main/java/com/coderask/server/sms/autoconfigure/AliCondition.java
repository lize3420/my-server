package com.coderask.server.sms.autoconfigure;

import com.coderask.server.sms.model.SmsConstant;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class AliCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var provider = context.getEnvironment().getProperty("coderask.sms.provider");
        return SmsConstant.PRODIVDER_ALI.equals(provider);
    }
}
