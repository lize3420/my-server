package com.coderask.server.verificationcode.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class VerificationCodeRecord {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column
    private String target;
    @Column
    private String type;
    @Column
    private String code;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime expiredTime;
    @Column
    private boolean success;
    @Column
    private String err;
}
