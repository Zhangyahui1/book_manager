package com.qlu.controller.admin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qlu.bean.Book;
import com.qlu.bean.StackRoom;
import com.qlu.bean.User;
import com.qlu.common.bean.Page;
import com.qlu.service.IUserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/add")
    public String to_add(){
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String add(User user, Model model){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        User one = userService.getOne(userQueryWrapper);
        if (one!=null&&one.getUsername()!=""){
            model.addAttribute("message","此名称已被占用");
            return "/admin/user/add";
        }else {
            user.setLevel(1);
            userService.save(user);
        }
        return "redirect:/admin/user/list";
    }

    @GetMapping("/list")
    public String to_list(){
        return "admin/user/list";
    }

    @GetMapping("/queryList")
    @ResponseBody
    public Page<User> queryList(Integer pageNum,Integer pageSize,Integer level){
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.notIn("level",2);
        if (level!=null){
            userWrapper.eq("level",level);
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageNum,pageSize);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page_mybatisPlus = userService.page(page, userWrapper);

        Page<User> res = new Page<>(pageSize, page_mybatisPlus);

        return res;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteUser(int id){
        boolean b = userService.removeById(id);
        return "success";
    }

    @GetMapping("/update")
    public String updateUser(int id,Model model){
        User user = userService.getById(id);
        model.addAttribute("updateUser",user);
        return "/admin/user/edit";
    }

    @PostMapping("/edit")
    public String edit(User user){
        boolean b = userService.updateById(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/exportUser")
    public void exportBookInfo(HttpServletResponse response){
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("用户名");
        row.createCell(2).setCellValue("邮箱");
        row.createCell(3).setCellValue("联系方式");
        row.createCell(4).setCellValue("密码");
        row.createCell(5).setCellValue("级别");

        List<User> list = userService.list();

        int index = 1;
        for (User user: list){

            String username = user.getUsername();
            String email = user.getEmail();
            String phone = user.getPhone();
            String password = user.getPassword();
            Integer l = user.getLevel();
            String level = null;
            if (l==0){
                level = "普通用户";
            }else if (l==1){
                level = "管理员";
            }else{
                level="超级管理员";
            }


            XSSFRow row1 = sheet.createRow(index);

            row1.createCell(0).setCellValue(index);
            row1.createCell(1).setCellValue(username);
            row1.createCell(2).setCellValue(email);
            row1.createCell(3).setCellValue(phone);
            row1.createCell(4).setCellValue(password);
            row1.createCell(5).setCellValue(level);

            index++;
        }


        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + "users.xlsx");

        try {
            xssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}