package com.jgc.springsecurity.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-08-03 16:39
 */
@RestController
@RequestMapping("/student")
public class StudentCtrl {

    @GetMapping("/list")
    public String list() {
        return "student list";
    }

    @GetMapping("/detail")
    public String detail() {
        return "student detail";
    }

    @GetMapping("/add")
    public String add() {
        return "student add";
    }

    @GetMapping("/modify")
    public String modify() {
        return "student modify";
    }

}
