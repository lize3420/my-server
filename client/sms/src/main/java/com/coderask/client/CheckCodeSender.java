package com.coderask.client;

import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.model.CheckCodeTypes;
import com.coderask.server.verificationcode.service.ICheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckCodeSender {

    @Autowired
    private ICheckCodeService checkCodeService;

    public void sendRegister(){
        Response response = checkCodeService.send("18612213420", CheckCodeTypes.register);
        System.out.println(response.toString());
    }
}
