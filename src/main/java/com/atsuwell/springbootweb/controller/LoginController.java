package com.atsuwell.springbootweb.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.util.Password;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    //    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam("password") String psw,
                        Map<String,Object> map, HttpSession session) {
        if(!StringUtils.isEmpty(username) && "123456".equals(psw)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码不正确");
            return "login";
        }


    }
}
