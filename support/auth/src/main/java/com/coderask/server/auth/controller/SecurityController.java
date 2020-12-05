package com.coderask.server.auth.controller;

import com.coderask.server.auth.protocol.RegisterByMobileRequest;
import com.coderask.server.auth.protocol.RegisterResponse;
import com.coderask.server.auth.protocol.Response;
import com.coderask.server.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/api/auth/registerByMobile")
    public Response<RegisterResponse> registerByMobile(@RequestBody RegisterByMobileRequest request){
        return securityService.registerByMobile(request);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
