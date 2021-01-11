package com.coderask.server.verificationcode.service;

public interface IVerificationCodeService {
    void send(String target, String type);

    void validVerificationCode(String target, String type, String verificationCode);
}
