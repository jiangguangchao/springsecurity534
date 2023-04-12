package com.jgc.springsecurity.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2022-04-13 15:40
 */
public class YourBeanFactoryPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new StaticMethodMatcherPointcut(){
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
//                if (method != null && method.getName().contains("list") && targetClass != null
//                        && targetClass.getName().contains("UserCtrl")) {
//
//                    return true;
//                }
                if (targetClass != null && targetClass.getName().contains("UserCtrl")) {

                    return true;
                }
                return false;
            }
        };
    }


}
