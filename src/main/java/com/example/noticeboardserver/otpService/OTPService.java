package com.example.noticeboardserver.otpService;

import com.example.noticeboardserver.entities.User;
import com.example.noticeboardserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service

public class OTPService {
    @Autowired
    OTPRepository otpRepository;
    @Autowired
    private  UserService userService;

    public void setOTP(String email, String otp) {
        User user = userService.getUserByEmail(email);
        OTP otp1 = new OTP();
        otp1.setOtp(otp);
        otp1.setUser(user);
        otp1.setCreatedTimestamp(LocalDateTime.now());
        otpRepository.save(otp1);
    }

    public boolean isPresent(String userName) {
        Optional<OTP> otp = otpRepository.presentData(userName);
        return otp.isPresent();
    }


    public void deleteByUserName(String userName) {
        otpRepository.deleteByUserName(userName);
    }

    public String generateOTP() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 6);
    }
    public boolean verifyOtp(String userName, String otp){
        Optional<OTP> otpPresent=otpRepository.verifyOtp(userName, otp);
        return otpPresent.isPresent();
    }
}
