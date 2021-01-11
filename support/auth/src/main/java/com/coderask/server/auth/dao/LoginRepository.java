package com.coderask.server.auth.dao;

import com.coderask.server.auth.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,String> {
    Optional<LoginEntity> findByLoginName(String loginName);
}
