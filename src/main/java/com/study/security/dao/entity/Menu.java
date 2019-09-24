package com.study.security.dao.entity;

import java.util.List;
import lombok.Data;

/**
 * FileName: Menu Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@Data
public class Menu{

  /**主键*/
  private int id;

  /**请求路径*/
  private String pattern;

  private List<Role> roles;

}
