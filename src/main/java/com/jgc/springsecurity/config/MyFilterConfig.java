package com.jgc.springsecurity.config;

import com.jgc.springsecurity.filter.MyFilter;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-09-27 16:02
 */


@Configuration
public class MyFilterConfig {


    /**
     * 这里有个简单的常识，之前并不知道，就是这种方式配置的bean的名称，就是方法的名称，比如当前这个bean
     * beanName就是getFiltera，所以下面的DelegatingFilterProxyRegistrationBean 对应的bean需要一个filter的
     * beanName, 就是getFiltera。所以这里默认的beanName并不是类名首字母小写。
     * @return
     */
    @Bean
    public MyFilter getFiltera() {
        return new MyFilter();
    }


    @Bean
    @Order(1)
    public DelegatingFilterProxyRegistrationBean myFilterRegistration() {
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("getFiltera");
        return bean;
    }


}
