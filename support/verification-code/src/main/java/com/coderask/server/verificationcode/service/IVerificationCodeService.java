package com.coderask.server.verificationcode.service;

import com.coderask.server.common.response.Response;

public interface IVerificationCodeService {
    Response send(String target,String type);
    boolean validVerificationCode(String target,String type,String verificationCode);
}
