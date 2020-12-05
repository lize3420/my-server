package com.coderask.server.sms.service;

import java.util.Map;

public interface SmsSendService {
    void send(String target, String tempateId, Map<String,String> params);
}
