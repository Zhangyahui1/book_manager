package com.qlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.bean.Book;
import com.qlu.bean.BookDonation;
import com.qlu.mapper.BookDonationMapper;
import com.qlu.mapper.BookMapper;
import com.qlu.service.IBookDonationService;
import com.qlu.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-03-17
 */
@Service
public class BookDonationServiceImpl extends ServiceImpl<BookDonationMapper, BookDonation> implements IBookDonationService {

}
