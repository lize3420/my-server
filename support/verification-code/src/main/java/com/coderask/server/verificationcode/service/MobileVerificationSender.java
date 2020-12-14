package com.coderask.server.verificationcode.service;

import com.coderask.server.common.util.PhoneUtil;
import com.coderask.server.sms.service.SmsSendService;
import com.coderask.server.verificationcode.autoconfigure.VerificationCodeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MobileVerificationSender implements IVerificationCodeSender {

    public static final String PARAMS_KEY_CODE = "code";

    @Autowired
    @Qualifier("smsSendService")
    private final SmsSendService smsSendService;
    @Autowired
    private final VerificationCodeProperties verificationCodeProperties;

    @Override
    public void send(String target, String type, String code) {
        smsSendService.send(target, verificationCodeProperties.getSmsTemplate().get("type"), Map.of(PARAMS_KEY_CODE, code));
    }

    @Override
    public boolean support(String target) {
        return PhoneUtil.isMobile(target);
    }

    @Override
    public long expireMinutes() {
        return 5;
    }
}
