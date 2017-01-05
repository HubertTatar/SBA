package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
    public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/user/users")
    public List<User> list() {
        return  userService.findAllusers();
    }

    @PostMapping("/user/userName")
    public User getByName(@RequestBody String userName) {
        return userService.findUserName(userName);
    }

    @PostMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return userService.save(user);
    }

}
