package com.coderask.server.sms.dao;

import com.coderask.server.sms.entity.CheckCodeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckCodeRecordRepository extends JpaRepository<CheckCodeRecord, String> {

    Optional<CheckCodeRecord> findByMobileAndTypeAndExpiredTimeBefore(String mobile, String type,long time);

    Optional<CheckCodeRecord> findByMobileAndTypeAndCodeAndExpiredTimeBefore(String mobile, String type, String code, long time);
}
