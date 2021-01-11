package com.coderask.server.auth.entity;

import com.coderask.server.mysql.UuidBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends UuidBaseEntity {

    @Column
    private String name;
    @Column
    private String photo;
    @Column
    private String password;
    /**
     * 账号没有过期
     */
    @Column
    private boolean expired;
    /**
     * 账号没有锁定
     */
    @Column
    private boolean locked;
    /**
     * 账号没有禁用
     */
    @Column
    private boolean enabled;

    /**
     * 密码是否过期
     */
    @Column
    private boolean credentialsExpired;
}
