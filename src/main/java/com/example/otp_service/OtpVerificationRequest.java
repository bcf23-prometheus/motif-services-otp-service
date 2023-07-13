package com.example.otp_service;

public class OtpVerificationRequest {




    private String email;

    public void setOtp(String otp) {
        this.otp = otp;
    }

    private String otp;
    public String getOtp() {
        return otp;
    }



    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
