package com.qlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.bean.User;
import com.qlu.mapper.UserMapper;
import com.qlu.service.IBookService;
import com.qlu.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @since 2020-03-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
