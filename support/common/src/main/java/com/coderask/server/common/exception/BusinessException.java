package com.coderask.server.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessException extends RuntimeException {
    private String code;
    private String err;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }


    public static BusinessException of(String code) {
        var result = new BusinessException();
        result.setCode(code);
        return result;
    }

    public static BusinessException of(Throwable e, String code) {
        var result = new BusinessException(e);
        result.setCode(code);
        return result;
    }
}
