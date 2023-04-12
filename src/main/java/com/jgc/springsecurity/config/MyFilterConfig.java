package com.jgc.springsecurity.config;

import com.jgc.springsecurity.ctrl.UserCtrl;
import com.jgc.springsecurity.filter.MyFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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


//@Configuration
//@ConditionalOnClass(UserCtrl.class)
//@ConditionalOnProperty(prefix = "myredis", name = "redisaaa", havingValue = "bbb", matchIfMissing = true)
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


    /**
     * 使用Order 注解不能决定过滤器的先后顺序，这个Order注解的作用
     * 应该是表示bean的先后顺序吧。所以要决定filter的顺序，需要调用
     * DelegatingFilterProxyRegistrationBean对象的setOrder方法
     * 设置order的值。
     * 比如这里设置为-200，因为spring security默认是-100，所以就可以使
     * 自定义过滤器在spring security过滤器前执行。实际效果，也确实这样。
     * 这里需要注意： order设置的值越小优先级越高，
     *
     * @return
     */
    @Bean
//    @Order(-200)
    public DelegatingFilterProxyRegistrationBean myFilterRegistration() {
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("getFiltera");
        bean.setOrder(-200);
        return bean;
    }


}
