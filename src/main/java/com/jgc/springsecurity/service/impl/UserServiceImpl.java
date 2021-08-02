package com.jgc.springsecurity.service.impl;

import com.jgc.springsecurity.dao.UserDao;
import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        List<User> list = userDao.getUser(u);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Role> getRoles(String username) {
        return null;
    }

    @Override
    public List<Permission> getPermissions(String username) {
        return userDao.getPermissions(username);
    }
}
