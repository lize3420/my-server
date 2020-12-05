package com.coderask.server.auth.entity;

import com.coderask.server.auth.model.LoginEntityType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="loginName")})
@Data
public class LoginEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String userId;
    @Column
    private String loginName;
    @Column
    private boolean credentialsNonExpired;
    @Column
    @Enumerated(EnumType.STRING)
    private LoginEntityType type;
}
