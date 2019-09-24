package com.study.security.config;

import java.util.Collection;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * FileName: MyAccessDecisionMagger Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Component
public class MyAccessDecisionMagger implements AccessDecisionManager {

  @Override
  public void decide(Authentication auth, Object object,
      Collection<ConfigAttribute> ca)
      throws AccessDeniedException, InsufficientAuthenticationException {
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    for (ConfigAttribute configAttribute : ca) {
      if ("ROLE_LOGIN".equals(configAttribute.getAttribute())
          && auth instanceof UsernamePasswordAuthenticationToken) {
        return;
      }
      for (GrantedAuthority authority : authorities) {
        if (configAttribute.getAttribute().equals(authority.getAuthority())) {
          return;
        }
      }
    }
    throw new AccessDeniedException("权限不足");
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}
