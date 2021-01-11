package com.coderask.server.auth.entity;

import com.coderask.server.mysql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginEntity extends BaseEntity {
    @Id
    private String loginName;
    @Column
    private String userId;
    @Column
    private String type;
}
