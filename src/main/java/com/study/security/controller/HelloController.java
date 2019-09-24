package com.study.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: HelloController Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello Stream";
  }

  @GetMapping("/admin/hello")
  public String admin() {
    return "hello admin!";
  }

  @GetMapping("/user/hello")
  public String user() {
    return "hello user!";
  }

  @GetMapping("/db/hello")
  public String dba() {
    return "hello dba!";
  }

}
