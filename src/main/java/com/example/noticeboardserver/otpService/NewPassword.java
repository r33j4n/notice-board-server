package com.example.noticeboardserver.otpService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewPassword {
    private String newPassword;
    private String confirmPassword;
    private String otp;
    private String email;


}
