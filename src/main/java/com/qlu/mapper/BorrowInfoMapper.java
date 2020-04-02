package com.qlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlu.bean.BorrowInfo;
import com.qlu.bean.vo.BorrowInfoAndBookAndUserVo;
import com.qlu.bean.vo.BorrowInfoAndBookVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {

    @Select("select borrow_info.*,book.book_name,book.author,book.publish_date,book.info,book.type," +
            "book.borrow_times,book.picture,book.srid,book.is_borrow " +
            "from book,borrow_info where book.id=borrow_info.bid and borrow_info.uid=#{uid} and is_return=#{isReturn}")
    List<BorrowInfoAndBookVo> getBorrowInfoAndBook(Page<BorrowInfoAndBookVo> pagination,
                                                   @Param("uid") Integer uid,
                                                   @Param("isReturn") Integer isReturn);


    @Select("select borrow_info.*,user.username,user.email,user.phone,user.level," +
            "book.book_name,book.author,book.publish_date,book.info,book.type," +
            "book.borrow_times,book.picture,book.srid,book.is_borrow" +
            " from borrow_info,book,user where borrow_info.bid=book.id and " +
            "borrow_info.uid=user.id and borrow_info.is_return=#{isReturn} and book.is_Borrow=#{isBorrow}")
    List<BorrowInfoAndBookAndUserVo> getBorrowInfoAndBookAndUser(Page<BorrowInfoAndBookAndUserVo> pagination,
                                                                 @Param("isReturn") Integer isReturn,
                                                                 @Param("isBorrow") Integer isBorrow);

}
