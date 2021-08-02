package com.jgc.springsecurity.service.impl;

import com.jgc.springsecurity.dao.UserDao;
import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-08-02 18:03
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getUser(String username) {
        User u = new User();
        u.setUsername(username);

        return null;
    }
}
