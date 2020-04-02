package com.qlu.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlu.bean.Book;
import com.qlu.bean.StackRoom;
import com.qlu.service.IBookService;
import com.qlu.service.IStackRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/stackRoom")
public class StackRoomController {

    @Autowired
    private IStackRoomService stackRoomService;

    @Autowired
    private IBookService bookService;

    @GetMapping("/add")
    public String to_addStackRoom(){
        return "/admin/stackRoom/add";
    }

    @GetMapping("/list")
    public String to_list(Model model){
        List<StackRoom> list = stackRoomService.list();
        model.addAttribute("stackRoomList",list);
        return "/admin/stackRoom/list";
    }

    @PostMapping("/add")
    public String addStackRoom(StackRoom stackRoom){
        stackRoomService.save(stackRoom);
        return "redirect:/admin/stackRoom/list";
    }

    @GetMapping("/edit/{id}")
    public String to_edit(@PathVariable int id,Model model){
        StackRoom stackRoom = stackRoomService.getById(id);
        model.addAttribute("stackRoom",stackRoom);
        return "/admin/stackRoom/edit";
    }

    @PostMapping("/edit")
    public String edit(StackRoom stackRoom){
        boolean b = stackRoomService.updateById(stackRoom);
        return "redirect:/admin/stackRoom/list";
    }

    /**
     * 注意数据转移
     * 删除书库的时候由于书籍当中有外键，也就是说，如果直接删除书库，此时有的书籍正好数与这个书库那么书籍怎么办？
     * 我们的解决方式
     * 给出提示，我们可以将这些书移动到另一个书库。
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteStackRoom(@PathVariable int id,Model model){
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("srid",id);
        List<Book> list = bookService.list(bookQueryWrapper);
        if (list.size()<1){
            //没有相应书库的书我们可以直接删除
            boolean b = stackRoomService.removeById(id);
            return "redirect:/admin/stackRoom/list";
        }else {
            QueryWrapper<StackRoom> stackRoomQueryWrapper = new QueryWrapper<>();
            stackRoomQueryWrapper.notIn("id",id);
            //可以移动到的书库列表
            List<StackRoom> stackRoomList = stackRoomService.list(stackRoomQueryWrapper);
            model.addAttribute("stackRoomList",stackRoomList);
            //想要删除的那个书库
            StackRoom stackRoom = stackRoomService.getById(id);
            model.addAttribute("stackRoom",stackRoom);
            return "/admin/stackRoom/moveBook";
        }
    }

    @PostMapping("/moveBook")
    public String moveBook(int fromId,int toId){

        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("srid",fromId);
        List<Book> list = bookService.list(bookQueryWrapper);
        for (Book book:list){
            book.setSrid(toId);
        }
        //将书籍信息完全迁移
        bookService.updateBatchById(list);

        //迁移后删除书库
        stackRoomService.removeById(fromId);
        return "redirect:/admin/stackRoom/list";
    }

    @GetMapping("/queryStackRoomList")
    @ResponseBody
    public List<StackRoom> stackRoomList(){
        return stackRoomService.list();
    }

}
