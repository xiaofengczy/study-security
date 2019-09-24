package com.study.security.dao.mapper;

import com.study.security.dao.entity.Role;
import com.study.security.dao.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * FileName: UserMapper Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper{

  User loadUserByUsername(@Param("username") String username);

  List<Role> getUserRolesByUid(@Param("id") Integer id);

  int saveUser(User user);

  User getUserById(Integer userId);
}
