package com.jgc.springsecurity.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

        //如果角色是admin，默认拥有所有权限，数据库无需配置权限

        System.out.println("开始执行自定义投票器逻辑");
        String uri = fi.getHttpRequest().getRequestURI();
        System.out.println("请求uri:" + uri);
        Collection<GrantedAuthority> list = (Collection<GrantedAuthority>) authentication.getAuthorities();
        if (CollectionUtils.isEmpty(list)) {
            return ACCESS_DENIED;
        }

        List<String> urls = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        list.forEach(g -> {
            if (g instanceof SimpleGrantedAuthority) {
                //角色
                roles.add(g.getAuthority());
            } else {
                urls.add(g.getAuthority());
            }
        });
        System.out.println("自定义投票器投票时打印当前登录用户URL权限：" + JSON.toJSONString(urls));
        System.out.println("自定义投票器投票时打印当前登录用户角色：" + JSON.toJSONString(roles));

        //如果用户拥有admin角色，则拥有所有权限
        if (roles.contains("admin")) {
            System.out.println("当前角色拥有[管理员]角色，权限校验通过");
            return ACCESS_GRANTED;
        }

        if (!urls.contains(uri)) {
            System.out.println("没有有权访问 ，不通过");
            return ACCESS_DENIED;
        }

        System.out.println("有权访问 ，通过");
        return ACCESS_GRANTED;

    }
}

