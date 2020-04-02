package com.qlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.bean.Book;
import com.qlu.mapper.BookMapper;
import com.qlu.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-03-17
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
