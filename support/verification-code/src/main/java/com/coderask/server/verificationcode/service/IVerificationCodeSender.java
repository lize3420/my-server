package com.coderask.server.verificationcode.service;

public interface IVerificationCodeSender {
    void send(String target, String type, String code);

    boolean support(String target);

    long expireMinutes();
}
