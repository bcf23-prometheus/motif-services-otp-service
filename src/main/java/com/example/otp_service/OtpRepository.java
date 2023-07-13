package com.example.otp_service;

import com.example.otp_service.Model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;






@Repository
public interface OtpRepository extends JpaRepository<OTP, String>, JpaSpecificationExecutor<OTP> {

    Optional<OTP> findByEmailOrderByTimeDesc(String email);



}