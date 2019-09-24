package com.study.security.dao.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * FileName: MyUserDetail Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Data
public class MyUserDetail implements UserDetails {

  private User user;

  private List<Role> roles;

  //获取当前用户对象所具有的角色信息
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  //获取当前用户对角的密码
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  //获取当前用户对象的用户名
  @Override
  public String getUsername() {
    return user.getUsername();
  }

  //当前账户是否过期
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //当前账户是否锁定
  @Override
  public boolean isAccountNonLocked() {
    return Objects.equals(user.getLocked(), (byte) 0) ? true : false;
  }

  //当前账户是否过期
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //当前账户是否可用
  @Override
  public boolean isEnabled() {
    return Objects.equals(user.getEnabled(), (byte) 1) ? true : false;
  }
}
