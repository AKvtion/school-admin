package com.dev.schooladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.schooladmin.controller.DTO.RolePms;
import com.dev.schooladmin.dao.RoleDao;
import com.dev.schooladmin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author fauchard
 * @since 2023-05-21 15:46:30
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, com.dev.schooladmin.entity.Role> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<RolePms> selectRoleAll() {
        return roleDao.selectRoleAll();
    }
}

