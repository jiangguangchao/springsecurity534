package com.jgc.springsecurity.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserCtr {

    @GetMapping("/list")
    public String list() {
        return "userList";
    }

    @GetMapping("/update")
    public String update() {
        return "userUpdate";
    }
    @GetMapping("/add")
    public String add() {
        return "userAdd";
    }
}
