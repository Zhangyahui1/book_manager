package com.qlu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.qlu.bean.User;
import com.qlu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }
    @GetMapping("/index")
    public String to_index(){
        return "/index";
    }

    @GetMapping("/login")
    public String to_login(){
        return "/login";
    }

    @GetMapping("/register")
    public String to_register(){
        return "register";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model,HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);
        if (user==null){
            model.addAttribute("message","没有此账号");
            return "/login";
        }else if (!user.getPassword().equals(password)){
            model.addAttribute("message","密码错误");
            return "/login";
        }else {
            session.setAttribute("user",user);
            Integer level = user.getLevel();
            if (level>0){
                return "/admin/index";
            }else {
                return "/index";
            }
        }
    }

    @PostMapping("/register")
    public String register(User user,Model model){
        user.setLevel(0);
        userService.save(user);
        model.addAttribute("message","注册成功请登录！");
        return "/login";
    }

    //查看用户是否已经注册
    @GetMapping("/testName")
    @ResponseBody
    public String testName(String username){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user==null){
            //此名称还未注册
            return "true";
        }else {
            return "false";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }
}
