package com.qlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.bean.Book;
import com.qlu.bean.StackRoom;
import com.qlu.mapper.BookMapper;
import com.qlu.mapper.StackRoomMapper;
import com.qlu.service.IBookService;
import com.qlu.service.IStackRoomService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-03-17
 */
@Service
public class StackRoomServiceImpl extends ServiceImpl<StackRoomMapper, StackRoom> implements IStackRoomService {

}
