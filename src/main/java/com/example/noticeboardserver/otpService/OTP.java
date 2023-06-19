package com.example.noticeboardserver.otpService;

import com.example.noticeboardserver.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="otptable")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="otp_id")
    private int otpId;
    @Column(name="otp")
    private String otp;
    @OneToOne
    @JoinColumn(name="usr_name",referencedColumnName = "userName")
    private User user;
    @Column(name="created_timestamp")
    private LocalDateTime createdTimestamp;


}
