package com.jgc.springsecurity.ctrl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.aspectj.AspectJPrecedenceInformation;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AbstractExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

//@RestController
public class HelloCtrl implements BeanFactoryAware {

    private static final Logger log = LoggerFactory.getLogger(HelloCtrl.class);

    private BeanFactory beanFactory;

//    @RequestMapping("/hello")
    public String hello() {

        if (beanFactory == null) {
            log.warn("beanFactory is null");
            return "beanFactory is null";
        }

        if (beanFactory instanceof DefaultListableBeanFactory) {
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
            BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition("helloCtrl");
            if (beanDefinition == null) {
                log.warn("helloCtrl beanDefinition is null");
                return "helloCtrl beanDefinition is null";
            }

            log.info("获取到 beanDefinition");
            Object obj = beanDefinition.getAttribute("org.springframework.aop.framework.autoproxy.AutoProxyUtils.originalTargetClass");
            log.info("obj class name: {}", obj);

            Object helloCtrlBean = defaultListableBeanFactory.getBean("helloCtrl");
            Advised advised = (Advised) helloCtrlBean;
            if (advised == null) {
                log.warn("advised is null");
                return "advised is null";
            }

            log.info("advised: {}", advised);


            Advisor[] advisors = advised.getAdvisors();
            if (advisors == null || advisors.length == 0) {
                log.warn("advisors is null");
                return "advisors is null";
            }

            for (Advisor advisor : advisors) {
                log.info("advisor class name: {}" , advisor.getClass().getName());
                //打印advisor可以看出代理类是因为产生的位置，比如当前这个HelloCtrl类 是因为
                // AspectTest这个类配置的aop代理了当前类，就可以从打印的信息看出来。
                log.info("advisor toString : {}", advisor.toString());
            }


        }

        log.info("测试日志配置 -- 这是info日志");

        log.debug("测试日志配置 -- 这是debug日志");

        return "hello security";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("设置beanFactory 属性");
        this.beanFactory = beanFactory;
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("jgcaaaab"));

    }

    public void change(Boolean f) {
        f = Boolean.parseBoolean("true");

    }
}
