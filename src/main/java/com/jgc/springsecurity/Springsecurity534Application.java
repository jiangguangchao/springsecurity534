package com.jgc.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
@SpringBootApplication
@MapperScan("com.jgc.springsecurity.dao")
public class Springsecurity534Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecurity534Application.class, args);
	}

}
