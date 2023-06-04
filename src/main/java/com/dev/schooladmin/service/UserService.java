package com.dev.schooladmin.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.schooladmin.controller.DTO.SignInData;
import com.dev.schooladmin.controller.DTO.UserRole;
import com.dev.schooladmin.entity.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author fauchard
 * @since 2023-03-25 16:36:02
 */
public interface UserService extends IService<User> {
    SaTokenInfo login(SignInData data);

    List<UserRole> selectUserRole();

    User selectOne(String username);

    Integer add(User user);

    Integer delById(Integer id);
}

