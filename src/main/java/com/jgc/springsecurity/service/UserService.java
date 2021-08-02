package com.jgc.springsecurity.service;

import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.User;

public interface UserService {

    public User getUser(String username);
}
