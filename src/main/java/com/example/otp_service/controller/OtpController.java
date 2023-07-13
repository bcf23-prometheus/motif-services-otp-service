package com.example.otp_service.controller;

import com.example.otp_service.EmailRequest;
import com.example.otp_service.OtpVerificationRequest;
import com.example.otp_service.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {
    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public void sendOtp(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        otpService.sendOtpByEmail(email);
    }

    @PostMapping("/verify-otp")
    public boolean verifyOtp(@RequestBody OtpVerificationRequest otpVerificationRequest) {
        String email = otpVerificationRequest.getEmail();
        String otp = otpVerificationRequest.getOtp();

        return otpService.verifyOtp(email, otp);
    }

}
