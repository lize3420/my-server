package com.coderask.server.auth.dao;

import com.coderask.server.auth.entity.UserEntity;
import com.coderask.server.auth.model.LoginUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {


    @Query("select new com.coderask.server.auth.model.LoginUserInfo(l,u) "
            + " from LoginEntity l "
            + " left join UserEntity u on l.userId = u.id"
            + " where l.loginName = :loginName "
    )
    Optional<LoginUserInfo> findLoginUserInfoByLoginName(String loginName);
}
