package com.study.security.controller;

import com.study.security.dao.entity.User;
import com.study.security.service.UserService;
import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: LoginController Description:
 *
 * @author xiaofengczy
 * @create 19-9-10
 */
@RestController
//@RequestMapping("/")
public class LoginController {

  @Resource
  private UserService userService;

//  @GetMapping("/login")
  public String login(Model model, @RequestParam(value = "error", required = false) String error) {
    if (error != null) {
      model.addAttribute("error", "用户名或密码错误");
    }
    return "forward:/login_page.html";
  }

  @GetMapping("/save/user")
  public int reg(String username,String password){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodePassword = encoder.encode(password);
    return userService.saveUser(username, encodePassword);
  }

  @GetMapping("/user")
  public User getUserById(@RequestParam("userId") Integer userId){
    return userService.getUserById(userId);
  }



}
