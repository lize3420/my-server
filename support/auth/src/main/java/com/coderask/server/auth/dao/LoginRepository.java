package com.coderask.server.auth.dao;

import com.coderask.server.auth.entity.LoginEntity;
import com.coderask.server.auth.model.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,String> {

    @Query("select new com.coderask.server.auth.model.MyUserDetails(l,u) "
    + " from LoginEntity l "
    + " left join UserEntity u on l.userId = u.id"
            + " where l.loginName = :loginName "
    )
    Optional<MyUserDetails> findUserDetails(String loginName);
}
