package com.example.noticeboardserver.JwtAuthenticationConfig;

import com.example.noticeboardserver.entities.User;
import com.example.noticeboardserver.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JWTauthentication {
    @Autowired
    private UserRepository userRepository;

    public String generateToken(String userName) {
        User user = userRepository.findById(userName).orElse(null);
        byte[] secretKeyBytes= Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey= Base64.getEncoder().encodeToString(secretKeyBytes);
        assert user != null;
        String token= Jwts.builder()
                .claim("userName",user.getUserName())
                .claim("role",user.getUserRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
        return token;

    }
}
