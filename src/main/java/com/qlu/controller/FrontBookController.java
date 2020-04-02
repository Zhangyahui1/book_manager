package com.qlu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qlu.bean.Book;
import com.qlu.bean.BorrowInfo;
import com.qlu.bean.StackRoom;
import com.qlu.common.bean.Page;
import com.qlu.service.IBookService;
import com.qlu.service.IBorrowInfoService;
import com.qlu.service.IStackRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/book")
public class FrontBookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IStackRoomService stackRoomService;

    @Autowired
    private IBorrowInfoService borrowInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public Page<Book> queryList(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "8") int pageSize,
                                Book book){
        System.out.println("=====");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Book> mybatisPage =
        new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum,pageSize);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(book.getBookName())){
            bookQueryWrapper.like("book_name",book.getBookName());
        }
        if (!StringUtils.isEmpty(book.getAuthor())){
            bookQueryWrapper.like("author",book.getAuthor());
        }
        if (!StringUtils.isEmpty(book.getType())){
            bookQueryWrapper.like("type",book.getType());
        }
        if (book.getIsBorrow()!=null){
            if (book.getIsBorrow()==1){
                //借出
                bookQueryWrapper.eq("is_borrow",1);
            }else {
                //未借出
                bookQueryWrapper.eq("is_borrow",0);
            }
        }
        bookQueryWrapper.orderByDesc("borrow_times");
        bookService.page(mybatisPage,bookQueryWrapper);
        Page<Book> page = new Page<>(pageSize,mybatisPage);
        return page;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Book> search(String query){

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name",query).or().like("author",query).or().like("type",query);
        List<Book> list = bookService.list(queryWrapper);
        return list;
    }

    @GetMapping("/info")
    public String info(int id, Model model){
        Book book = bookService.getById(id);
        StackRoom stackRoom = stackRoomService.getById(book.getSrid());
        BorrowInfo borrowInfo = borrowInfoService.getOne(new QueryWrapper<BorrowInfo>().eq("bid", id).eq("is_return", 0));

        model.addAttribute("bookInfo",book);
        model.addAttribute("stackRoom",stackRoom);
        model.addAttribute("borrowInfo", borrowInfo);


        return "/book/info";
    }

    @PostMapping("/order")
    @ResponseBody
    @Transactional
    public String order(int uid,int bid){
        //查询预约借了几本书  如果预约了5本就不用动
        int count = borrowInfoService.count(new QueryWrapper<BorrowInfo>().eq("is_return", 0));
        if (count==5){
            return "wrong";
        }

        Book book = new Book();
        book.setId(bid);
        //改为预约状态
        book.setIsBorrow(1);
        bookService.updateById(book);

        //借阅信息
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBid(bid);
        borrowInfo.setBorrowTime(new Date());
        borrowInfo.setUid(uid);
        Calendar instance = Calendar.getInstance();
        //归还时间
        instance.add(Calendar.DATE,14);
        borrowInfo.setReturnTime(instance.getTime());
        //初始为0表示没有归还
        borrowInfo.setIsReturn(0);
        borrowInfoService.save(borrowInfo);
        return "success";
    }


}
