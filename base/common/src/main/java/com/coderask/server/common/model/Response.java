package com.coderask.server.common.model;


import lombok.Data;

@Data
public class Response {
    private int code;
    private String err;

    public boolean isSuccess(){
        return code == 0;
    }


    public static Response ofSuccess(){
        Response result = new Response();
        result.code = RESPONSE_CODE_SUCCESS;
        return result;
    }

    public static Response ofFail(String err){
        Response result = new Response();
        result.code = RESPONSE_CODE_COMMON_FAIL;
        result.err = err;
        return result;
    }

    public static Response ofFail(int code,String err){
        Response result = new Response();
        result.code = code;
        result.err = err;
        return result;
    }

    public static final int RESPONSE_CODE_SUCCESS = 0;
    public static final int RESPONSE_CODE_COMMON_FAIL = 1000;
}
