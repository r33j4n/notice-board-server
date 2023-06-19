package com.example.noticeboardserver.controllers;

import com.example.noticeboardserver.DTO.LoginResponse;
import com.example.noticeboardserver.entities.User;
import com.example.noticeboardserver.services.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
   public User postDetails(@RequestBody User user)
        {
            return userService.saveDetails(user);
        }
        @GetMapping("/getAllUser")

        public List<User> getAllUserDetails()
        {
            return userService.getAllUserDetails();
        }
        @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user)
        {
            String userName=user.getUserName();
            String password=user.getPassword();
            System.out.println(user);
            LoginResponse loginResponse=userService.performlogin(userName,password);
            return ResponseEntity.ok(loginResponse);

        }


}
