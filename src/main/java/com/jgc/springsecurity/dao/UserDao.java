package com.jgc.springsecurity.dao;

import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> getUser(User user);
    public List<Role> getRoles(String username);
    public List<Permission> getPermissions(String username);
    public void saveUser(User user);
}
