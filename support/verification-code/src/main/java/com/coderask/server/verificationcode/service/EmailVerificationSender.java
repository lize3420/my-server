package com.coderask.server.verificationcode.service;

import org.springframework.stereotype.Service;

@Service
public class EmailVerificationSender implements IVerificationCodeSender {

    @Override
    public void send(String target, String type, String code) {

    }

    @Override
    public boolean support(String target) {
        return false;
    }

    @Override
    public long expireMinutes() {
        return 0;
    }
}
