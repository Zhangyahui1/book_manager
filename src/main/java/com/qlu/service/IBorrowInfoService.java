package com.qlu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qlu.bean.BorrowInfo;
import com.qlu.bean.vo.BorrowInfoAndBookAndUserVo;
import com.qlu.bean.vo.BorrowInfoAndBookVo;


/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-03-17
 */
public interface IBorrowInfoService extends IService<BorrowInfo> {
    Page<BorrowInfoAndBookVo> getBorrowInfoAndBook(Page<BorrowInfoAndBookVo> page, Integer uid,Integer isReturn);

    Page<BorrowInfoAndBookAndUserVo> getBorrowInfoAndBookAndUser(Page<BorrowInfoAndBookAndUserVo> page, Integer isReturn, Integer isBorrow);

}
