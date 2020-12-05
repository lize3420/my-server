package com.coderask.server.sms.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CheckCodeUtil {

    public String randomCheckCode() {
        return String.valueOf(new Random().nextInt(999999));
    }
}
