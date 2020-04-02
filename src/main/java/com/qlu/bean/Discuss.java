package com.qlu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discuss {

    @TableId(type = IdType.AUTO)
    private Integer id;


    private Integer bid;

    /**
     * 评论信息
     */
    private String info;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;


    private String username;

}
