package com.dev.schooladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.schooladmin.controller.DTO.RolePms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author fauchard
 * @since 2023-05-21 15:46:30
 */
@Mapper
public interface RoleDao extends BaseMapper<com.dev.schooladmin.entity.Role> {

    List<RolePms> selectRoleAll();
}

