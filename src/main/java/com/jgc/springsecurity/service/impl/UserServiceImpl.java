package com.jgc.springsecurity.service.impl;

import com.jgc.springsecurity.dao.UserDao;
import com.jgc.springsecurity.domain.*;
import com.jgc.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-08-02 18:03
 */

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static List<String> list = new ArrayList();

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private UserRoleDao userRoleDao;


    @Override
    public User getUser(String username) {
        
        if (transactionManager == null) {
            log.info("dstm is null");
        } else {
            log.info("dstm is not null");
        }

        TransactionStatus ts = transactionManager.getTransaction(new TransactionDefinition() {
        });

        transactionManager.commit(ts);


        User u = new User();
        u.setUsername(username);
        List<User> list = userDao.getUser(u);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User getUser(Integer id) {
        User u = new User();
        u.setId(id);
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

    public AtomicInteger atom = new AtomicInteger(0);

    @Override
    public synchronized String testTx() {

        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        User u = getUser(1);
        u.setAge(u.getAge() + 1);
        int result = userDao.updateUserAge(u);
        if (result < 1) {
            log.error("更新失败");
            return null;
        }
        atom.incrementAndGet();
        transactionManager.commit(ts);

        return null;
    }

    @Override
    public void setAtom(Integer a) {
        atom.set(a);
    }

    @Override
    public Integer getAtom() {
        return atom.get();
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 5;
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        b = 7;
        a = 3;
        list.add("bac");
        System.out.println("11");
        System.out.println("22");
        System.out.println("33");

    }
}
