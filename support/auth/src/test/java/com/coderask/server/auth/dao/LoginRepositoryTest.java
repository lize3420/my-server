package com.coderask.server.auth.dao;

import com.coderask.server.auth.entity.LoginEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void testSaveLoginConflect(){
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setLoginName("12345");
        loginRepository.saveAndFlush(loginEntity);
        LoginEntity loginEntity2 = new LoginEntity();
        loginEntity2.setLoginName("12345");
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->{
            loginRepository.saveAndFlush(loginEntity2);
        });
    }
}
