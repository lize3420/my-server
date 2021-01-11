package com.coderask.server.behaviorverify.service;

import com.coderask.server.behaviorverify.model.BehaviorVerifyResponseConstant;
import com.coderask.server.common.exception.BusinessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class VerifyCodeService {

    public void checkVerifyCode(String code){
        var request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        var session = request.getSession(false);
        if(session == null){
            throw BusinessException.of(BehaviorVerifyResponseConstant.VERIFY_CODE_ERR);
        }
        String target = (String) session.getAttribute("verify_code");
        if(target == null || !target.equals(code)){
            throw BusinessException.of(BehaviorVerifyResponseConstant.VERIFY_CODE_ERR);
        }

    }
}
