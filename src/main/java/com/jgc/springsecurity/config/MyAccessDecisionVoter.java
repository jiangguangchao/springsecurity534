package com.jgc.springsecurity.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @program: springsecurity534
 * @description: 自定义投票器，可以参考spring security 默认自带投票器 WebExpressionVoter
 * @author:
 * @create: 2021-08-03 11:54
 */
public class MyAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        System.out.println("开始执行自定义投票器");
        String uri = object.getHttpRequest().getRequestURI();
        System.out.println("uri:" + uri);
        if (StringUtils.isEmpty(uri)) {
            System.out.println("uri is null");
        } else {
            if (authentication.isAuthenticated()) {
                Collection<GrantedAuthority> list = (Collection<GrantedAuthority>) authentication.getAuthorities();
                if (!CollectionUtils.isEmpty(list)) {
                    if (list.contains(uri)) {
                        System.out.println("有权访问 ，通过");
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
