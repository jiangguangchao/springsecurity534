package com.jgc.springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    /**
     * 这里的MyObjectPostProcessor 是自定义的ObjectPostProcessor。在MyObjectPostProcessor中，
     * 会添加一个自定义的投票器到spring security的决策器中，默认情况下spring security的投票器中已经
     * 存在一个投票器，就是WebExpressionVoter。这个投票器的作用是验证是否有权访问url，比如你想限制某个url
     * 的访问，要求必须有管理员角色才能访问，你需要有显式的配置代码。现在是想不需要显式的配置代码，只需要数据库
     * 保存url对应的角色关系。就能直接判定是否有权限。所以这里用自定义一个投票器。因为投票器中可以获取到
     * Authentication 和url，Authentication里可以包含当前用户对应的角色所拥有的所有url，然后判断当前请求
     * 的url是否包含在内，就可以判断是否有权限了。
     *
     * 实际运行之后，发现没有起作用，访问没有权限的url一样能通过。debug发现是默认决策器的原因。spring security
     * 中默认使用的决策器是AffirmativeBased。这个决策器的作用是，只要有投票器同意，那决策器决策的结果就是同意。
     * 上面提到，spring security中默认提供了一个WebExpressionVoter投票器。默认这个投票器只是判断是否已经登录。
     * 在向决策器中添加自定义投票器的时候，是添加到这个默认投票器后面了，所以先执行默认投票器，因为结果是同意。所以直接
     * 就放行了。
     * 如果在添加投票器的时候加到默认投票器之前，结果如何呢？
     * 结果仍然一样，因为默认决策器AffirmativeBased的判定逻辑是：只要有同意的，那结果就是同意，不论投票器的顺序，
     * 哪怕是AffirmativeBased中的前面几个投票器都是反对，最后一个同意，那就是同意了。这里可以改为另一个决策器UnanimousBased
     * 这个决策器的逻辑是：只要有反对的，那结果就是反对。（这个决策器没有测试过）
     *
     * 这里还有一个办法，就是重写默认的投票器WebExpressionVoter的 vote（）方法。
     * 经过测试，这种方法可行。
     *
     * 整体看下上面的过程，用到了自定义的ObjectPostProcessor。其实spring security启动过程 有很多的 init（），
     * beforeConfig（）,config()等方法, 可以在某个beforeConfig（）方法中使用自定义的投票器替换默认的投票器。
     * 这个没有测试，懒得测试了。仅仅是记录下这种思路。
     *
     * 网上见过一种方法是向spring容器中放入一个自定义的决策器，然后这个决策器中包含自定义的投票器。没测试过这种能否起作用。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/jquery-3.6.0.min.js").permitAll()
                .antMatchers("/home.html", "/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
        http.csrf().disable();

//        List<ExpressionUrlAuthorizationConfigurer> configs = http.getConfigurers(ExpressionUrlAuthorizationConfigurer.class);
//        if (CollectionUtils.isEmpty(configs)) {
//            System.out.println("configs is null");
//        } else {
//            System.out.println("configs size:" + configs.size());
//            for (ExpressionUrlAuthorizationConfigurer config : configs) {
//                System.out.println("这里添加post processor");
//                config.addObjectPostProcessor(new MyObjectPostProcessor());
//            }
//        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}

