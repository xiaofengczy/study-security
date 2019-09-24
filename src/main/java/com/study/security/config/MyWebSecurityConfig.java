package com.study.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * FileName: MyWebSecurityConfig Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
//@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admin345")
        .roles("ADMIN", "USER")
        .and()
        .withUser("zhangsan")
        .password("123")
        .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/admin/**")
        .hasRole("ADMIN")
        .antMatchers("/user/**")
        .access("hasAnyRole('ADMIN','USER')")
        .antMatchers("/db/**")
        .access("hasRole('ADMIN') and hasRole('DBA')")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login_page")
        .loginProcessingUrl("/login")
        .usernameParameter("name")
        .passwordParameter("password")
        .successHandler((httpServletRequest, resp, auth) -> {
          Object principal = auth.getPrincipal();
          resp.setContentType("application/json;charset=utf-8");
          PrintWriter out = resp.getWriter();
          resp.setStatus(200);
          Map<String, Object> map = new HashMap<>();
          map.put("status", 200);
          map.put("msg", principal);
          ObjectMapper om = new ObjectMapper();
          out.write(om.writeValueAsString(map));
          out.flush();
          out.close();
        })
        .failureHandler((req, resp, e) -> {
          resp.setContentType("application/json;charset=utf-8");
          PrintWriter out = resp.getWriter();
          resp.setStatus(401);
          Map<String, Object> map = new HashMap<>();
          map.put("status", 401);
          if (e instanceof LockedException) {
            map.put("msg", "账户被锁定，登录失败!");
          } else if (e instanceof BadCredentialsException) {
            map.put("msg", "账户名或密码输入错误，登录失败!");
          } else if (e instanceof DisabledException) {
            map.put("msg", "账户被禁用，登录失败!");
          } else if (e instanceof AccountExpiredException) {
            map.put("msg", "账户过期，登录失败!");
          } else if (e instanceof CredentialsExpiredException) {
            map.put("msg", "密码过期，登录失败!");
          } else {
            map.put("msg", "登录失败!");
          }
          ObjectMapper obj = new ObjectMapper();
          out.write(obj.writeValueAsString(map));
          out.flush();
          out.close();
        })
        .and()
        .logout()
        .logoutUrl("/logout")
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .addLogoutHandler((req, resp, auth) -> {
        })
        .logoutSuccessHandler(
            (request, response, authentication) -> response.sendRedirect("/login_page"))
        .permitAll()
        .and()
        .csrf()
        .disable();
  }
}
