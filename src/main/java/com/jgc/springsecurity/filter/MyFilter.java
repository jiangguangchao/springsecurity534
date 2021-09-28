package com.jgc.springsecurity.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-09-27 15:59
 */


//@Component
public class MyFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("----------这里是自定义过滤器---------------");
        chain.doFilter(request, response);
    }
}
