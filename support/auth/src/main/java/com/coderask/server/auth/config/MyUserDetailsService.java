package com.coderask.server.auth.config;

import com.coderask.server.auth.dao.LoginRepository;
import com.coderask.server.auth.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUserDetails> loginUser = loginRepository.findUserDetails(username);
        return loginUser.orElseThrow(()->new UsernameNotFoundException("用户不存在或密码错误!"));
    }
}
