package com.coderask.server.common.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private String code;
    private String err;

    private BusinessException(){
        super();
    }

    private BusinessException(Throwable throwable){
        super(throwable);
    }


    public static BusinessException of(String code){
        var result = new BusinessException();
        result.setCode(code);
        return result;
    }

    public static BusinessException of(Throwable e,String code){
        var result = new BusinessException(e);
        result.setCode(code);
        return result;
    }
}
