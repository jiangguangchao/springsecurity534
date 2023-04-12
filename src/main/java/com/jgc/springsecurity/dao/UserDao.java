package com.jgc.springsecurity.dao;

import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> getUser(User user);
    public List<Role> getRoles(String username);
    public List<Permission> getPermissions(String username);
    public void saveUser(User user);
    public void saveUserForMap(Map user);
    public int updateUserAge(User user);
}
