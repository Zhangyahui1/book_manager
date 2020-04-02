package com.qlu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 书籍
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    /**
     * 书籍id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 借阅次数
     */
    private Integer borrowTimes;

    /**
     * 书籍封面
     */
    private String picture;

    /**
     * 书库信息 id
     */
    private Integer srid;

    /**
     * 是否已经被借出 2是借出,1是预约，0是未借出
     */
    private Integer isBorrow;

}
