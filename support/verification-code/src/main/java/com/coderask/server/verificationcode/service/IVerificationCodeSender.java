package com.coderask.server.verificationcode.service;

import com.coderask.server.common.response.Response;

public interface IVerificationCodeSender {
    Response send(String target, String type,String code);
    boolean support(String target);
    long expireMinutes();
}
