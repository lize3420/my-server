package com.coderask.server.verificationcode.service;

public interface IVerificationCodeService {
    void send(String target, String type);

    boolean validVerificationCode(String target, String type, String verificationCode);
}
