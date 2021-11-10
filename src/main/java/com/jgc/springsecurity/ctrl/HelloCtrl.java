package com.jgc.springsecurity.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCtrl {

    @RequestMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
