package com.qlu.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @GetMapping
    public String index(){
        return "admin/index";
    }

    @GetMapping("/index")
    public String to_index(){
        return "admin/index_v1";
    };

}
