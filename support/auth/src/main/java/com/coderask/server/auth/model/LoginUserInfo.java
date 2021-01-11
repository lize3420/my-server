package com.coderask.server.auth.model;

import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class LoginUserInfo implements UserDetails {
    private LoginEntity loginEntity;
    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userEntity.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userEntity.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !userEntity.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isEnabled();
    }

    public LoginUser toLoginUser() {
        var result = new LoginUser();
        result.setUserId(userEntity.getId());
        result.setUserName(userEntity.getName());
        result.setPhoto(userEntity.getPhoto());
        result.setAuthorities(getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var user = (LoginUserInfo) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
