package com.dev.schooladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.schooladmin.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author fauchard
 * @since 2023-03-25 16:35:54
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

