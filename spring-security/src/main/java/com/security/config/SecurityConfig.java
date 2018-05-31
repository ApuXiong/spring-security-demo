package com.security.config;

import com.security.authentication.validate.code.ValidateCodeAuthenticationFilter;
import com.security.authentication.validate.sms.SmsCodeAuthenticationProvider;
import com.security.authentication.validate.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler successHandler;

    @Autowired
    protected AuthenticationFailureHandler failureHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    ValidateCodeAuthenticationFilter validateCodeAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(smsCodeAuthenticationSecurityConfig).and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/user/mobile")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login/form")
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/hello")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
                .addFilterBefore(validateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new SmsCodeAuthenticationProvider());
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
