package com.coderask.server.verificationcode;

import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.service.VerificationCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendCheckCodeTest {

    @Autowired
    private VerificationCodeService aliCheckCodeService;

    @Test
    public void testSendCheckCode(){
        Response response = aliCheckCodeService.send("13466718118","register");
        System.out.println(response.toString());

    }
}
