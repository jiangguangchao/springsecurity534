package com.jgc.springsecurity.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @program: springsecurity534
 * @description:  重写 WebExpressionVoter的投票方法
 * @author:
 * @create: 2021-08-04 10:53
 */
public class MyWebExpressionVoter extends WebExpressionVoter {
    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
        int result = super.vote(authentication, fi, attributes);
        if (result != 1) {
            return result;
        }

        System.out.println("开始执行自定义投票器逻辑");
        String uri = fi.getHttpRequest().getRequestURI();
        System.out.println("请求uri:" + uri);
        Collection<GrantedAuthority> list = (Collection<GrantedAuthority>) authentication.getAuthorities();
        if (CollectionUtils.isEmpty(list)) {
            return ACCESS_DENIED;
        }

        if (!list.contains(uri)) {
            System.out.println("没有有权访问 ，不通过");
            return ACCESS_DENIED;
        }

        System.out.println("有权访问 ，通过");
        return ACCESS_GRANTED;

    }
}
