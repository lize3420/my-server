package com.coderask.server.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class HttpResponse extends Response{
    private int httpStatus;

    public static HttpResponse ofHttpStatus(int httpStatus){
        var result = new HttpResponse();
        result.setHttpStatus(httpStatus);
        if(httpStatus == 200){
            result.setCode(ResponseConstant.RESPONSE_CODE_SUCCESS);
        }else if(httpStatus == HttpStatus.UNAUTHORIZED.value()){
            result.setCode(ResponseConstant.RESPONSE_CODE_FAIL_UNAUTHORIZED);
        }else{
            result.setCode(ResponseConstant.RESPONSE_CODE_FAIL_HTTP);
        }
        return result;
    }
}
