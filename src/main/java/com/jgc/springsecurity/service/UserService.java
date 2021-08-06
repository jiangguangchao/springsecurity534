package com.jgc.springsecurity.service;

import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.User;

import java.util.List;

public interface UserService {

    public User getUser(String username);

    public List<Role> getRoles(String username);

    public List<Permission> getPermissions(String username);

    public void saveUser(User user);
}
