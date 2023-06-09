package com.dev.schooladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.schooladmin.controller.DTO.UserRole;
import com.dev.schooladmin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author fauchard
 * @since 2023-03-25 16:35:54
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    List<String> getPermissionListByUserId(@Param("id") Integer id);

    List<UserRole> selectUserRole();

    Integer addUser(User user);

    Integer addUserRole(User user);

    Integer delUserRoleById(Integer id);

    Integer delUserById(Integer i);
}

