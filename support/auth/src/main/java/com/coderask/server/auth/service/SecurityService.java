package com.coderask.server.auth.service;

import com.coderask.server.auth.dao.LoginRepository;
import com.coderask.server.auth.dao.UserRepository;
import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.entity.UserEntity;
import com.coderask.server.auth.protocol.RegisterByMobileRequest;
import com.coderask.server.auth.protocol.RegisterResponse;
import com.coderask.server.auth.protocol.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SecurityService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Response<RegisterResponse> registerByMobile(RegisterByMobileRequest request){

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setCredentialsNonExpired(true);
        loginEntity.setLoginName(request.getMobile());
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setEnabled(true);
        userEntity.setName("用户"+ new Random().nextInt(9999));
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        try{
            loginEntity = loginRepository.saveAndFlush(loginEntity);
        }catch (ConstraintViolationException e){
            return Response.ofFail("用户已存在");
        }
        userEntity = userRepository.save(userEntity);
        loginEntity.setUserId(userEntity.getId());
        loginRepository.save(loginEntity);
        return Response.ofSuccess();
    }
}
