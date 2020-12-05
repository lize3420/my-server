package com.coderask.server.verificationcode.dao;

import com.coderask.server.verificationcode.entity.VerificationCodeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRecordRepository extends JpaRepository<VerificationCodeRecord, String> {

    Optional<VerificationCodeRecord> findByMobileAndTypeAndExpiredTimeBefore(String mobile, String type, long time);

    Optional<VerificationCodeRecord> findByMobileAndTypeAndCodeAndExpiredTimeBefore(String mobile, String type, String code, long time);
}
