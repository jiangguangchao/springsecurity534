package com.jgc.springsecurity.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2023-04-10 15:26
 */
public class MyMethodArgResolverComposite extends HandlerMethodArgumentResolverComposite {

    public MyMethodArgResolverComposite(){
        addResolver();
    }

    public void addResolver() {
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
        resolvers.add(new RequestResponseBodyMethodProcessor(null));
        resolvers.add(new ServletModelAttributeMethodProcessor(true));
        addResolvers(resolvers);
    }
}
