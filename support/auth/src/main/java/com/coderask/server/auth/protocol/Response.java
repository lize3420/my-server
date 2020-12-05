package com.coderask.server.auth.protocol;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String err;
    private T data;

    public static <T> Response<T> ofSuccess(){
        Response<T> result = new Response<>();
        result.code = CODE_SUCCESS;
        return result;
    }

    public static <T> Response<T> ofSuccess(T data){
        Response<T> result = new Response<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        return result;
    }

    public static <T> Response<T> ofFail(int code,String err){
        Response<T> result = new Response<>();
        result.code = code;
        result.err = err;
        return result;
    }

    public static <T> Response<T> ofFail(String err){
        Response<T> result = new Response<>();
        result.code = CODE_ERR_COMMON;
        result.err = err;
        return result;
    }

    public static int CODE_SUCCESS = 0;
    public static int CODE_ERR_COMMON = 1000;

}
