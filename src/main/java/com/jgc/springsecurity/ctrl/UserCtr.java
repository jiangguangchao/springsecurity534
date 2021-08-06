package com.jgc.springsecurity.ctrl;

import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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
    public String add(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        userService.saveUser(user);
        System.out.println("添加用户成功");
        return "user add success";
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode("xm");
        System.out.println(encoderPassword);
    }
}
