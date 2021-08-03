package com.jgc.springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(User.withUsername("jgc").password("jgc1").roles("admin"));
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        List<ExpressionUrlAuthorizationConfigurer> configs = http.getConfigurers(ExpressionUrlAuthorizationConfigurer.class);
        if (CollectionUtils.isEmpty(configs)) {
            System.out.println("configs is null");
        } else {
            System.out.println("configs size:" + configs.size());
            for (ExpressionUrlAuthorizationConfigurer config : configs) {
                System.out.println("这里添加post processor");
                config.addObjectPostProcessor(new MyObjectPostProcessor());
            }
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }



}
