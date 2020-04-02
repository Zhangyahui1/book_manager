package com.qlu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlu.bean.Book;
import com.qlu.service.IBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFenYe {

    @Autowired
    IBookService bookService;

    @Test
    public void test1(){
        Page<Book> bookPage = new Page<>(1,2);
        bookPage = bookService.page(bookPage);
        System.out.println(bookPage);
    }

}
