package com.jgc.springsecurity.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-10 15:20
 */

@Aspect
@Component
public class AspectTest {

    private static final Logger log = LoggerFactory.getLogger(AspectTest.class);

    @Pointcut("execution(public String com.jgc.springsecurity.ctrl.HelloCtrl.*())")
    public void helloCtrl() {}


    @Around("helloCtrl()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        log.info("类名[{}],方法名[{}],入参[{}] 信息记录", className, methodName, JSON.toJSONString(args));
        Object result = joinPoint.proceed();
        return result;
    }
}
