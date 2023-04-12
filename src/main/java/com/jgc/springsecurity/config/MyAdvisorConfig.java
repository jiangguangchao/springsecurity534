package com.jgc.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2022-04-14 16:44
 */


//@Configuration
public class MyAdvisorConfig {


    @Bean
    public YourBeanFactoryPointcutAdvisor yourBeanFactoryPointcutAdvisor() {
        YourBeanFactoryPointcutAdvisor yourBeanFactoryPointcutAdvisor = new YourBeanFactoryPointcutAdvisor();
        yourBeanFactoryPointcutAdvisor.setAdvice(new YourMethodIntercept());
        return yourBeanFactoryPointcutAdvisor;
    }
}
