package com.jgc.springsecurity.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 启动类上必须加上注解@EnableAspectJAutoProxy，否则@Aspect不生效，无法使用aop
 */
@Aspect
@Component
public class AspectTest {

    private static final Logger log = LoggerFactory.getLogger(AspectTest.class);

    @Pointcut("execution(public * com.jgc.springsecurity.service.UserService.*(com.jgc.springsecurity.domain.User))")
    public void userService(){}


    @Around("userService()")
    public Object logUserService(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("方法名称：{}, 参数：{}", name, args);
        Object result = joinPoint.proceed(args);
        return result;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String str = encoder.encode("jgc");
        log.info("加密后：{}", str);
    }

}
