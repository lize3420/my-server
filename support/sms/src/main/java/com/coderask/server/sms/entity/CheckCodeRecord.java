package com.coderask.server.sms.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class CheckCodeRecord {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column
    private String mobile;
    @Column
    private String code;
    @Column
    private String type;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime expiredTime;
    @Column
    private boolean success;
    @Column
    private String err;
}
