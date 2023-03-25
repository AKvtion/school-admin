package com.dev.schooladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.schooladmin.dao.UserDao;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author fauchard
 * @since 2023-03-25 16:36:03
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

