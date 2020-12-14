package com.coderask.server.common.service;

import com.coderask.server.common.exception.BusinessException;
import com.coderask.server.common.response.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class Exceptionhandle {

    @ExceptionHandler(value =Exception.class)
    public Response exceptionHandler(BusinessException e){
        return Response.ofFail(e.getCode(),e.getErr());
    }
}
