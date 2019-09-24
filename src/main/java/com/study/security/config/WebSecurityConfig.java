package com.study.security.config;

import com.study.security.service.UserService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * FileName: WebSecurityConfig Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  UserService userService;

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers("/admin/**")
//        .hasRole("admin")
//        .antMatchers("/db/**")
//        .hasRole("dba")
//        .antMatchers("/user/**")
//        .hasRole("user")
//        .and()
//        .formLogin()
//        .loginProcessingUrl("/login")
//        .permitAll()
//        .and()
//        .csrf()
//        .disable();
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
          @Override
          public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            object.setSecurityMetadataSource(cfisms());
            object.setAccessDecisionManager(cadm());
            return object;
          }
        })
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .permitAll()
        .and()
        .csrf()
        .disable();
  }

  @Bean
  MyAccessDecisionMagger cadm() {
    return new MyAccessDecisionMagger();
  }

  @Bean
  MyMetadataSource cfisms() {
    return new MyMetadataSource();
  }

}
