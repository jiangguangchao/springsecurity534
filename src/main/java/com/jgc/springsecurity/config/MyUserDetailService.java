package com.jgc.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u = userService.getUser(username);
        SecurityUserDetail userDetails = new SecurityUserDetail();
        userDetails.setUsername(username);
        userDetails.setPassword(u.getPassword());
        List<Permission> permissions = userService.getPermissions(username);

        Collection<GrantedAuthority> collection = new ArrayList<>();
        if (permissions.size() > 0) {

            permissions.forEach(p -> {
                GrantedAuthority g = () -> {
                    return p.getUrl();
                };
                collection.add(g);
            });
        }
        userDetails.setAuthorities(collection);
        System.out.println("获取用户时打印用户权限：" + JSON.toJSONString(userDetails));
        return userDetails;
    }
}
