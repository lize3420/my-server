package com.coderask.server.verificationcode.service;

import com.coderask.server.common.response.Response;
import org.springframework.stereotype.Service;

@Service
public class MobileVerificationSender implements IVerificationCodeSender{

    @Override
    public Response send(String target, String type, String code) {
        return null;
    }

    @Override
    public boolean support(String target) {

        return false;
    }

    @Override
    public long expireMinutes() {
        return 5;
    }
}
