package com.coderask.server.sms;

import com.coderask.server.common.response.Response;
import com.coderask.server.sms.service.AliCheckCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendCheckCodeTest {

    @Autowired
    private AliCheckCodeService aliCheckCodeService;

    @Test
    public void testSendCheckCode(){
        Response response = aliCheckCodeService.send("13466718118","register");
        System.out.println(response.toString());

    }
}
