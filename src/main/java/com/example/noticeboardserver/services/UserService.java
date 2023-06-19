package com.example.noticeboardserver.services;

import com.example.noticeboardserver.DTO.LoginResponse;
import com.example.noticeboardserver.JwtAuthenticationConfig.JWTauthentication;
import com.example.noticeboardserver.entities.User;
import com.example.noticeboardserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import java.util.List;
@Data
@NoArgsConstructor

@Service
public class UserService {
    private UserRepository userRepository;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private JWTauthentication jwtA;

    @Autowired
    public UserService(UserRepository userRepository , BCryptPasswordEncoder bCryptPasswordEncoder, JWTauthentication jwtA) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtA = jwtA;
    }



//    public User saveDetails(User user) {
//    return userRepository.save(user);
//    }
    public List<User> getAllUserDetails()
    {
        return userRepository.findAll();
    }
    public User saveDetails(User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public LoginResponse performlogin(String userName, String password) {
        User user = userRepository.findById(userName).orElse(null);
        System.out.println(user);
        if (user == null) {
            return new LoginResponse("User not found", false,null);
        } else {
            boolean isPasswordMatched = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (isPasswordMatched) {
                String token= jwtA.generateToken(userName);
                System.out.println(token);
                return new LoginResponse("Login Successful", true,token);
            } else {
                return new LoginResponse("Incorrect Password", false,null);
            }
        }


    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
