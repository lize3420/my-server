package com.coderask.server.verificationcode.controller;

import com.coderask.server.behaviorverify.service.VerifyCodeService;
import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.protocol.SendVerificationCodeRequest;
import com.coderask.server.verificationcode.protocol.SendVerificationCodeResponse;
import com.coderask.server.verificationcode.service.VerificationCodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;
    private final VerifyCodeService verifyCodeService;

    /**
     * 发送验证码
     * @param request
     * @return
     */
    @PostMapping("/api/verification/send")
    public Response sendVerificationCode(@RequestBody SendVerificationCodeRequest request){
        verifyCodeService.checkVerifyCode(request.getVerifyCode());
        verificationCodeService.send(request.getTarget(),request.getType());
        return SendVerificationCodeResponse.ofSuccess();
    }
}
