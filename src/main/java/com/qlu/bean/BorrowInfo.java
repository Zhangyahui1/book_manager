package com.qlu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 借阅信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowInfo {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 书id
     */
    private Integer bid;

    /**
     * 借阅时间
     */
    private Date borrowTime;

    /**
     * 归还时间
     */
    private Date returnTime;

    /**
     * 是否归还
     */
    private Integer isReturn;

}
