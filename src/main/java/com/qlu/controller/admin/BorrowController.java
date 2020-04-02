package com.qlu.controller.admin;

import com.qlu.bean.Book;
import com.qlu.bean.BorrowInfo;
import com.qlu.bean.vo.BorrowInfoAndBookAndUserVo;
import com.qlu.common.bean.Page;
import com.qlu.service.IBookService;
import com.qlu.service.IBorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/admin/borrow")
public class BorrowController {

    @Autowired
    private IBorrowInfoService borrowInfoService;

    @GetMapping("/borrowInfo")
    public String to_borrowInfo(){
        return "/admin/borrow/borrowInfo";
    }

    @GetMapping("/orderInfo")
    public String to_orderInfo(){
        return "/admin/borrow/orderInfo";
    }

    @GetMapping("/returnInfo")
    public String to_returnInfo(){
        return "/admin/borrow/returnInfo";
    }
    //0 , 1 预约,
    //1 , 0 归还,
    //0 , 2 正被借的
    @RequestMapping("/showBorrowList")
    @ResponseBody
    public Page<BorrowInfoAndBookAndUserVo> showBorrowList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        return new Page<>(pageSize,
                borrowInfoService.getBorrowInfoAndBookAndUser(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<BorrowInfoAndBookAndUserVo>(pageNum, pageSize),
                        0,
                        2));
    }

    @RequestMapping("/showOrderList")
    @ResponseBody
    public Page<BorrowInfoAndBookAndUserVo> showOrderList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        return new Page<>(pageSize,
                borrowInfoService.getBorrowInfoAndBookAndUser(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<BorrowInfoAndBookAndUserVo>(pageNum, pageSize),
                        0,
                        1));
    }

    @RequestMapping("/showReturnList")
    @ResponseBody
    public Page<BorrowInfoAndBookAndUserVo> showReturnList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        return new Page<>(pageSize,
                borrowInfoService.getBorrowInfoAndBookAndUser(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<BorrowInfoAndBookAndUserVo>(pageNum, pageSize),
                        1,
                        0));
    }

    @Autowired
    private IBookService bookService;

    @RequestMapping("/borrow")
    @ResponseBody
    @Transactional
    public String borrow(int bid){
        //book 改成 2
        Book book = bookService.getById(bid);
        book.setIsBorrow(2);
        //借书成功借阅量加一
        book.setBorrowTimes(book.getBorrowTimes()+1);
        bookService.updateById(book);
        return "success";
    }

    @RequestMapping("/returnBook")
    @ResponseBody
    @Transactional
    public String returnBook(int id,int bid){

        //此条借阅信息更改
        BorrowInfo borrowInfo = borrowInfoService.getById(id);
        //归还
        borrowInfo.setIsReturn(1);
        borrowInfo.setReturnTime(new Date());
        borrowInfoService.updateById(borrowInfo);

        //还书籍
        Book book = bookService.getById(bid);
        book.setIsBorrow(0);
        bookService.updateById(book);
        return "success";
    }

}
