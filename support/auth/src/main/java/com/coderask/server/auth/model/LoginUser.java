package com.coderask.server.auth.model;

import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class LoginUser {
    private String userId;
    private String userName;
    private String photo;
    private List<String> authorities;

    public LoginUser(){

    }

    public LoginUser(LoginEntity loginEntity, UserEntity userEntity){
        this.userId = userEntity.getId();
        this.userName = userEntity.getName();
        this.photo = userEntity.getPhoto();
    }

}
