package com.coderask.server.auth.model;

import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class MyUserDetails implements UserDetails {
    private String id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private List<GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(LoginEntity loginEntity, UserEntity userEntity) {
        id = userEntity.getId();
        username = loginEntity.getLoginName();
        password = userEntity.getPassword();
        accountNonExpired = userEntity.isAccountNonExpired();
        accountNonLocked = userEntity.isAccountNonLocked();
        enabled = userEntity.isEnabled();
        credentialsNonExpired = loginEntity.isCredentialsNonExpired();
    }
}
