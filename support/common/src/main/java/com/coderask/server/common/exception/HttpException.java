package com.coderask.server.common.exception;

import com.coderask.server.common.response.ResponseConstant;
import lombok.Data;

@Data
public class HttpException extends BusinessException{
    private int httpStatus;

    public static HttpException of(int httpStatus){
        var result = new HttpException();
        result.setHttpStatus(httpStatus);
        result.setCode(ResponseConstant.RESPONSE_CODE_FAIL_HTTP);
        return result;
    }
}
