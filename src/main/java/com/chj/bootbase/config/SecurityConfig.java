package com.chj.bootbase.config;

import com.chj.bootbase.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalAuthentication
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Autowired
    AuthFailureHandler authFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("forgot-password","/email-template/**", "/findMember/**", "/resource/**","/register/**", "/error/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/" ,"/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email").passwordParameter("password")
                .successHandler(authSuccessHandler).failureHandler(authFailureHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true).clearAuthentication(true)
                .permitAll()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(memberService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user@gmali.com").password("password").roles("USER")
                .and()
                .withUser("admin@gmail.com").password("password").roles("ADMIN");
        auth.authenticationProvider(authenticationProvider());
    }
}
