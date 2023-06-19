package com.example.noticeboardserver.otpService;

import com.example.noticeboardserver.entities.User;
import com.example.noticeboardserver.services.EmailService;
import com.example.noticeboardserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forgotPassword")

public class OTPController {
    @Autowired
    private  OTPService otpService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/requestMail")
    public ResponseEntity<String> requestOTP(@RequestBody Map<String,String> response) {
        User user = userService.getUserByEmail(response.get("email"));
        String email = response.get("email");
        System.out.println(email);
        if (user == null) {
            return ResponseEntity.ok("User not found");
        }
        if (otpService.isPresent(user.getUserName())) {
            otpService.deleteByUserName(user.getUserName());
        }
        String otp = otpService.generateOTP();
        otpService.setOTP(email,otp);
        emailService.sendPasswordResetEmail(email, otp);
        return ResponseEntity.ok("OTP sent successfully");
    }
    @PostMapping("/verifyOTP")
    public ResponseEntity<String> verifyOTP(@RequestBody Map<String,String> response) {
        User user = userService.getUserByEmail(response.get("email"));
        if(otpService.verifyOtp(user.getUserName(),response.get("otp")))
        {
            return ResponseEntity.ok("OTP verified successfully");
        }

        return ResponseEntity.ok("OTP not verified");
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String,String> response) {
        User user1 = userService.getUserByEmail(response.get("email"));
        if (user1 == null) {
            return ResponseEntity.ok("User not found");
        }
//        user1.setPassword(user.getPassword());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(response.get("password"));
        user1.setPassword(encryptedPassword);
        userService.saveDetails(user1);
        return ResponseEntity.ok("Password changed successfully");
    }

}
