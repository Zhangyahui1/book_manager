package com.qlu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书库
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StackRoom {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 书库名称
     */
    private String name;

    /**
     * 书库地址
     */
    private String location;

}
