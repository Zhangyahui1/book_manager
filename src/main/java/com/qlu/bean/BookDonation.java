package com.qlu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDonation {
    /**
     * 书籍id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 书籍名称
     */
    private String bookName;


    /**
     * 作者
     */
    private String author;

    /**
     * 出版日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date publishDate;

    /**
     * 书籍简介
     */
    private String info;

    /**
     * 书籍类型
     */
    private String type;


    /**
     * 书籍封面
     */
    private String picture;

    /**
     * 状态0是还未入库, 1是已经上架入库
     */
    private Integer status;

}
