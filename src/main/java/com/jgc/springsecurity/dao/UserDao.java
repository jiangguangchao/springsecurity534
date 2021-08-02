package com.jgc.springsecurity.dao;

import com.jgc.springsecurity.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> getUser(User user);
}
