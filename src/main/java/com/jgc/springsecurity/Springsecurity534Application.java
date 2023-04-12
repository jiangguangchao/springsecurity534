package com.jgc.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ServletComponentScan
@SpringBootApplication
@MapperScan("com.jgc.springsecurity.dao")
//如果使用@Aspect注解，必须在启动类上加上@EnableAspectJAutoProxy注解，才能使@Aspect注解生效
//@EnableAspectJAutoProxy
public class Springsecurity534Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecurity534Application.class, args);
	}

}
