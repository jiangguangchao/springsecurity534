package com.jgc.springsecurity.config;

import com.jgc.springsecurity.domain.Auth;
import com.jgc.springsecurity.domain.Role;
import com.jgc.springsecurity.domain.dto.RoleDto;
import org.apache.catalina.LifecycleListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.withUsername("jgc").password("jgc").roles("admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RoleDto roleAdmin = new RoleDto();
        roleAdmin.setRoleId(1);
        roleAdmin.setRoleName("admin");
        RoleDto roleLeader = new RoleDto();
        roleLeader.setRoleId(2);
        roleLeader.setRoleName("leader");

        Auth auth1 = new Auth();
        auth1.setUrl("/user/list");
        Auth auth2 = new Auth();
        auth2.setUrl("/user/add");
        Auth auth3 = new Auth();
        auth3.setUrl("/user/update");

        List<Auth> list1 = new ArrayList<>();
        List<Auth> list2 = new ArrayList<>();

        list1.add(auth1);
        list1.add(auth2);
        list1.add(auth3);

        list2.add(auth1);

        roleAdmin.setList(list1);
        roleLeader.setList(list2);

        List<RoleDto> listRole = new ArrayList<>();
        listRole.add(roleAdmin);
        listRole.add(roleLeader);

        for (RoleDto roleDto: listRole) {
            for (Auth auth: roleDto.getList()) {
                http.authorizeRequests().antMatchers(auth.getUrl()).hasRole(roleDto.getRoleName());
            }
        }

        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
