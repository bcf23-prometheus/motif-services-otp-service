package com.example.otp_service.service;

import com.example.otp_service.Model.OTP;
import com.example.otp_service.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final JavaMailSender mailSender;
    private final OtpRepository otpRepossitory;


    @Value("${spring.mail.username}")
    private String emailFrom;

//    @Autowired
//    public OtpService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    public void sendOtpByEmail(String email) {
        // Generate OTP
        String otp = RandomStringUtils.randomNumeric(6);


        // Send OTP via email
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(emailFrom);
//        message.setTo(email);
//        message.setSubject("OTP Verification");
//        message.setText("Your OTP is: " + otp);

        var otp_data = new OTP();

        var existinOtp = otpRepossitory.findByEmailOrderByTimeDesc(email);
        if(existinOtp.isPresent()){
            otp_data = existinOtp.get();
            otp_data.setOtp(otp);
            otpRepossitory.save(otp_data);

        }


        otp_data.setOtp(otp);
        otp_data.setEmail(email);
        otp_data.setTime(new Date());

        sendNotification(email,otp);
        otpRepossitory.save(otp_data);


        //mailSender.send(message);
    }



    private void sendNotification(String email, String otp) {
        String apiUrl = "http://localhost:8083/api/notifications";
        String payload = "{\"email\":\"" + email + "\", \"message\":\"" + otp + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Handle the response if needed
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<OTP> otpEntityOptional = otpRepossitory.findByEmailOrderByTimeDesc(email);

        if (otpEntityOptional.isPresent()) {
            OTP otpEntity = otpEntityOptional.get();
            return otpEntity.getOtp().equals(otp);
        }

        return false;
    }

}
