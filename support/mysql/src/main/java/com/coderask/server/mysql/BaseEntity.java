package com.coderask.server.mysql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Column
    private boolean deleted;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime updateTime;

}
