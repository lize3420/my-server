package com.coderask.server.common.response;

import lombok.Data;

@Data
public class Response {
    public static final String RESPONSE_CODE_SUCCESS = "ok";
    public static final String RESPONSE_CODE_COMMON_FAIL = "fail";
    private String code;
    private String err;

    public static Response ofSuccess() {
        Response result = new Response();
        result.code = RESPONSE_CODE_SUCCESS;
        return result;
    }

    public static Response ofFail(String code) {
        Response result = new Response();
        result.code = code;
        return result;
    }

    public static Response ofFail(String code, String err) {
        Response result = new Response();
        result.code = code;
        result.err = err;
        return result;
    }

    public boolean isSuccess() {
        return RESPONSE_CODE_SUCCESS.equals(code);
    }
}
