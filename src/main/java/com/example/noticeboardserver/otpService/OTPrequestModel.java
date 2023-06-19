package com.example.noticeboardserver.otpService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OTPrequestModel{
    private String email;
    private String otp;



}
