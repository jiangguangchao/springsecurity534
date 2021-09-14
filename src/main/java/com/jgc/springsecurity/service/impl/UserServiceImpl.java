package com.jgc.springsecurity.service.impl;

import com.alibaba.fastjson.JSON;
import com.jgc.springsecurity.dao.UserDao;
import com.jgc.springsecurity.dao.UserRoleDao;
import com.jgc.springsecurity.domain.*;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-08-02 18:03
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public User getUser(String username) {
        User u = new User();
        u.setUsername(username);
//        u.setCreateTime(new Date());
        List<User> list = userDao.getUser(u);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Role> getRoles(String username) {
        return userDao.getRoles(username);
    }

    @Override
    public List<Permission> getPermissions(String username) {
        return userDao.getPermissions(username);
    }

    @Override
    public void saveUser(User user) {
        user.setCreateTime(new Date());
        user.setBirthday(new Date());
        user.setStartWrokTime(new Date());
        user.setLeaveTime(new Date());
        userDao.saveUser(user);

        //新创建的用户默认 添加普通用户角色，以便使用（如果没有基本的角色，登录后无法看到主页，也不能访问登录后的其他页面）
//        UserRole ur = new UserRole();
//        ur.setRoleId(2);
//        ur.setUsername(user.getUsername());
//        ur.setStatus(33333333);
//        userRoleDao.saveUserRole(ur);
    }

    @Override
    public void saveUserForMap(Map user) {
        userDao.saveUserForMap(user);
    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap();
        map.put("date", new Date());
        System.out.println(map.get("date").getClass().getName());

        User u = new User();
        u.setId(1);
        u.setCreateTime(new Date());
        Map<String, Object> map2 = JSON.toJavaObject((JSON) JSON.toJSON(u), Map.class);
        System.out.println(map2.get("createTime").getClass().getName());
        System.out.println(JSON.toJSONString(map2));
    }
}
