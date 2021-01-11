package com.coderask.server.auth.controller;

import com.coderask.server.auth.protocol.*;
import com.coderask.server.auth.service.AuthService;
import com.coderask.server.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api/auth/registerByMobile")
    public Response registerByMobile(@RequestBody RegisterByMobileRequest request){
        return authService.registerByMobile(request);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
