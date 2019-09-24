package com.study.security.dao.entity;

import lombok.Data;

/**
 * FileName: User Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Data
public class User {

  private int id;

  private String username;

  private String password;

  private Byte enabled;

  private Byte locked;
}
