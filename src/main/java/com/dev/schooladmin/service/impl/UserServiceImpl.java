package com.dev.schooladmin.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.schooladmin.controller.DTO.SignInData;
import com.dev.schooladmin.controller.DTO.UserRole;
import com.dev.schooladmin.dao.UserDao;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (User)表服务实现类
 *
 * @author fauchard
 * @since 2023-03-25 16:36:03
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public SaTokenInfo login(SignInData data) {
        //查询id,name,password字段，条件(数据库的列名name）和用户传进来的字符串data.getName()
        User user = userDao.selectOne(new QueryWrapper<User>().select("id,username,password").eq("username", data.getUsername()));
        if (user == null) {
            return null;
        }
        //将用户传入的密码进行加密，用户加密后的值 和 数据库查询出来的值进行对比
        if (!DigestUtil.md5Hex(data.getPassword()).equals(user.getPassword())) {
            return null;
        }
        //sa-token的登录方法
        StpUtil.login(user.getId());
        return StpUtil.getTokenInfo();  //返回token
    }

    @Override
    public List<UserRole> selectUserRole() {
        return userDao.selectUserRole();
    }

    @Override
    public User selectOne(String username) {
        return userDao.selectOne(new QueryWrapper<User>().select("id,avatar,username").eq("username", username));
    }

    @Override
    public Integer add(User user) {
        Integer flag = userDao.addUser(user);
        if(Validator.isNotEmpty(flag) ){
            userDao.addUserRole(user);
        }
        return flag;
    }

    @Override
    public Integer delById(Integer id) {
        Integer flag = userDao.delUserRoleById(id);
        if (Validator.isNotEmpty(flag)){
            userDao.delUserById(id);
        }
        return flag;
    }
}

