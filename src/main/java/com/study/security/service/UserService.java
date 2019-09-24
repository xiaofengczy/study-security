package com.study.security.service;

import com.study.security.dao.entity.MyUserDetail;
import com.study.security.dao.entity.User;
import com.study.security.dao.mapper.UserMapper;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * FileName: UserService Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Service
public class UserService implements UserDetailsService{

  @Resource
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.loadUserByUsername(username);
    if(Objects.isNull(user)){
      throw new UsernameNotFoundException("账户不存在!");
    }
    MyUserDetail myUserDetail = new MyUserDetail();
    myUserDetail.setUser(user);
    myUserDetail.setRoles(userMapper.getUserRolesByUid(user.getId()));
    return myUserDetail;
  }


  public int saveUser(String username,String password){
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setEnabled((byte)1);
    user.setLocked((byte)0);
    return userMapper.saveUser(user);
  }

  public User getUserById(Integer userId) {
    return userMapper.getUserById(userId);
  }
}
