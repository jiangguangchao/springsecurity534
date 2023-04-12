package com.jgc.springsecurity.config;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2022-04-13 15:44
 */
public class YourMethodIntercept implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(YourMethodIntercept.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("方法调用前，自定义逻辑。。。");
        Object result = invocation.proceed();
        log.info("方法调用后，自定义逻辑。。。");
        return result;
    }
}
