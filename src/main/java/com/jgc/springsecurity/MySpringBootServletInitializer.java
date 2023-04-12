package com.jgc.springsecurity;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2022-04-20 17:12
 */
public class MySpringBootServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        System.out.println("MySpringBootServletInitializer.configure()");
        return application.sources(Springsecurity534Application.class);
    }
}
