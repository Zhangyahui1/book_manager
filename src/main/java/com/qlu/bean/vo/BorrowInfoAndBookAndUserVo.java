package com.qlu.bean.vo;

import lombok.Data;

@Data
public class BorrowInfoAndBookAndUserVo extends BorrowInfoAndBookVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
//    private String password;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户级别（0普通用户，1管理员，2超级管理员）
     */
    private Integer level;
    /**
     * 头像
     */
    private String photo;
}
