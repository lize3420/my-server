package com.coderask.server.verificationcode.service;

import com.coderask.server.common.response.Response;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationSender implements IVerificationCodeSender{
    @Override
    public Response send(String target, String type) {
        return null;
    }
}
