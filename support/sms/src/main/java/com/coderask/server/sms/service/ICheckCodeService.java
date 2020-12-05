package com.coderask.server.sms.service;

import com.coderask.server.common.response.Response;

public interface ICheckCodeService {
    Response send(String mobile,String type);
    boolean validCheckCode(String mobile,String type,String checkCode);
}
