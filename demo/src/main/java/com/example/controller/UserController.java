package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User reqisterUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> json) throws ServletException {
        String username = json.get("username");
        String password = json.get("password");
        if(username == null || password == null) {
            throw new ServletException("username or password is empty");
        }

        User user = userService.findUserName(username);
        if (user == null) {
            throw new ServletException("User not found");
        }

        String pwd = user.getPassword();
        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login credentials");
        }

        return Jwts.builder().setSubject(username)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }
}
