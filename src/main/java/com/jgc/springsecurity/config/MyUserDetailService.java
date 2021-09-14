
package com.jgc.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.jgc.springsecurity.domain.Permission;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u = userService.getUser(username);
        SecurityUserDetail userDetails = new SecurityUserDetail();
        userDetails.setUsername(username);
        userDetails.setPassword(u.getPassword());

        Collection<GrantedAuthority> collection = new ArrayList<>();

        //将角色放入SecurityUserDetail中
        List<Role> roles = userService.getRoles(username);
        System.out.println("数据库查询用户[" + username + "]时，用户角色：" + JSON.toJSONString(roles));
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(r -> {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(r.getRole());
                collection.add(authority);
            });
        }

        //将角色对应的url放入到SecurityUserDetail中
        List<Permission> permissions = userService.getPermissions(username);
        System.out.println("数据库查询用户时，用户权限：" + JSON.toJSONString(permissions));
        if (permissions.size() > 0) {

            permissions.forEach(p -> {
                GrantedAuthority g = () -> {
                    return p.getUrl();
                };
                collection.add(g);
            });
        }

        userDetails.setAuthorities(collection);
        System.out.println("查询到用户信息：" + JSON.toJSONString(userDetails));
        return userDetails;
    }
}