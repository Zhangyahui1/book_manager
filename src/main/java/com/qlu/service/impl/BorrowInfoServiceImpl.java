package com.qlu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.bean.BorrowInfo;
import com.qlu.bean.vo.BorrowInfoAndBookAndUserVo;
import com.qlu.bean.vo.BorrowInfoAndBookVo;
import com.qlu.mapper.BorrowInfoMapper;
import com.qlu.service.IBorrowInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-03-17
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements IBorrowInfoService {

    @Override
    public Page<BorrowInfoAndBookVo> getBorrowInfoAndBook(Page<BorrowInfoAndBookVo> page,Integer uid,Integer isReturn) {
        return page.setRecords(this.baseMapper.getBorrowInfoAndBook(page, uid,isReturn));
    }

    @Override
    public Page<BorrowInfoAndBookAndUserVo> getBorrowInfoAndBookAndUser(Page<BorrowInfoAndBookAndUserVo> page, Integer isReturn, Integer isBorrow) {
        return page.setRecords(this.baseMapper.getBorrowInfoAndBookAndUser(page,isReturn,isBorrow));
    }
}
