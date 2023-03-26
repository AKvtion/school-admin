package com.dev.schooladmin.base.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dev.schooladmin.dao.UserDao;
import com.dev.schooladmin.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class authorGive implements StpInterface {
    @Resource
    private UserDao userDao;

    //添加某些权限可以访问的
    @Override
    public List<String> getPermissionList(Object o, String s) {
        Integer id = Integer.parseInt((String) o);
        User user = userDao.selectOne(new QueryWrapper<User>().select("id,name,password,email").eq("id", id));
        //“*”权限表示什么都可以访问
        List<String> list = new ArrayList<String>();
        //“*”权限表示什么都可以访问
        list.add(user.getName());
        list.add("user.selectOne");
        list.add("user.selectAll");
        return list;
    }

    //添加某些角色可以访问的
    @Override
    public List<String> getRoleList(Object o, String lognType) {
        //o属性就是刚刚绑定的id，通过这个id去数据库查询权限
        Integer id = Integer.parseInt((String) o);
        User user = userDao.selectOne(new QueryWrapper<User>().select("id,name,password,email").eq("id", id));
        List<String> list = new ArrayList<String>();
        //“*”权限表示什么都可以访问
        list.add(user.getName());
        list.add("teacher");
        list.add("student");
        return list;
    }
}