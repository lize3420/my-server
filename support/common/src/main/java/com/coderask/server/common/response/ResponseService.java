package com.coderask.server.common.response;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseService {

    private Map<String, String> codeMap = new HashMap<>();


    public String getErrByCode(String code) {
        return codeMap.get(code);
    }

    public void registerCode(String code, String err) {
        codeMap.put(code, err);
    }

}
