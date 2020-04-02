package com.qlu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@RequestMapping("/user")
public class FrontUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/editUserInfo")
    public String to_editUserInfo(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "/user/editUserInfo";
    }
    @GetMapping("/editPassword")
    public String to_editPassword(){
        return "/user/editPassword";
    }

    @GetMapping("/info")
    public String info(){
        return "/user/info";
    }



    @PostMapping("/editUserInfo")
    public String editUserInfo(User user,HttpSession session){
        boolean b = userService.updateById(user);
        if (b){
            session.setAttribute("user",user);
        }
        return "redirect:/user/info";
    }

    @PostMapping("/editPassword")
    @ResponseBody
    public String editPassword(String oldPassword,String password,Integer uid){
        User test = userService.getById(uid);
        if (!test.getPassword().equals(oldPassword)){
            return "fail";
        }
        User user = new User();
        user.setPassword(password);
        user.setId(uid);
        userService.updateById(user);
        return "success";
    }



}
