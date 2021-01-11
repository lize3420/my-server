package com.coderask.server.auth.service;

import com.coderask.server.auth.dao.LoginRepository;
import com.coderask.server.auth.dao.UserRepository;
import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.entity.UserEntity;
import com.coderask.server.auth.model.AuthResponseConstant;
import com.coderask.server.auth.model.LoginEntityType;
import com.coderask.server.auth.protocol.RegisterByMobileRequest;
import com.coderask.server.common.exception.BusinessException;
import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.model.VerificationCodeTypes;
import com.coderask.server.verificationcode.service.VerificationCodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final VerificationCodeService verificationCodeService;


    public Response registerByMobile(RegisterByMobileRequest request) {

        verificationCodeService.validVerificationCode(request.getMobile(), VerificationCodeTypes.register,request.getCode());

        var old = loginRepository.findByLoginName(request.getMobile());
        if (old.isPresent()) {
            throw BusinessException.of(AuthResponseConstant.RESPONSE_CODE_MOBILE_REGISTERED);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setEnabled(true);
        userEntity.setCredentialsExpired(false);
        userEntity.setName("用户" + new Random().nextInt(9999));
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity = userRepository.save(userEntity);

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setLoginName(request.getMobile());
        loginEntity.setType(LoginEntityType.mobile);
        loginEntity.setUserId(userEntity.getId());
        loginEntity = loginRepository.saveAndFlush(loginEntity);

        loginRepository.save(loginEntity);
        return Response.ofSuccess();
    }


}