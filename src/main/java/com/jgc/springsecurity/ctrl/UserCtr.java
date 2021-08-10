package com.jgc.springsecurity.ctrl;

import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//@Transactional
public class UserCtr {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String list() {
        return "userList";
    }

    @GetMapping("/update")
    public String update() {
        return "userUpdate";
    }

    @PostMapping("/add")
    public String add(User user) {
        System.out.println("打印userService:" + userService.getClass().getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        userService.saveUser(user);
        System.out.println("添加用户成功 userService:" + userService);
        return "user add success";
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode("jgc");
        System.out.println(encoderPassword);
    }
}
