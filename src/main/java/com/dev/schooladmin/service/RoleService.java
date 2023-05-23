package com.dev.schooladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.schooladmin.controller.DTO.RolePms;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author fauchard
 * @since 2023-05-21 15:46:30
 */
public interface RoleService extends IService<com.dev.schooladmin.entity.Role> {

    List<RolePms> selectRoleAll();
}

