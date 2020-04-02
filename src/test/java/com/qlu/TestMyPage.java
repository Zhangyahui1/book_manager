package com.qlu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlu.bean.vo.BorrowInfoAndBookAndUserVo;
import com.qlu.service.IBorrowInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMyPage {

    @Autowired
    private IBorrowInfoService borrowInfoService;

    @Test
    public void test1(){
        Page<BorrowInfoAndBookAndUserVo> objectPage = new Page<>(1, 2);
        //0 , 1 预约,
        //1 , 0 归还,
        //0 , 2 正被借的
        Page<BorrowInfoAndBookAndUserVo> borrowInfoAndBookAndUser = borrowInfoService.getBorrowInfoAndBookAndUser(objectPage, 0, 1);
        System.out.println(borrowInfoAndBookAndUser);
    }

}
