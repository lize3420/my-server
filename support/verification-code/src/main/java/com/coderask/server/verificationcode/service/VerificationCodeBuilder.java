package com.coderask.server.verificationcode.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerificationCodeBuilder {

    public String randomCode() {
        return String.valueOf(new Random().nextInt(999999));
    }
}
