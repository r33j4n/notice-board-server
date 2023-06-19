package com.example.noticeboardserver.otpService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP ,Integer> {

    @Query("select o from OTP o where o.user.userName = :userName")
    Optional<OTP> presentData(@Param("userName") String userName);


    @Modifying
    @Transactional
    @Query("delete from OTP o where o.user.userName = :userName")
    void deleteByUserName(String userName);

    @Query("select o from OTP o where o.user.userName = :userName and o.otp = :otp")
    Optional<OTP> verifyOtp(@Param("userName") String userName,@Param("otp") String otp);


}
