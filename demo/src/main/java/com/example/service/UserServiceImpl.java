package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllusers() {
        return userDao.findAll();
    }

    @Override
    public User findUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }
}
