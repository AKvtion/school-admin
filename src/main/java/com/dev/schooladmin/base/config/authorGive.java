package com.dev.schooladmin.base.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dev.schooladmin.dao.UserDao;
import com.dev.schooladmin.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component      // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class authorGive implements StpInterface {
    @Resource
    private UserDao userDao;

    //添加某些权限可以访问的
    @Override
    public List<String> getPermissionList(Object o, String s) {
//        //“*”权限表示什么都可以访问
//        List<String> list = new ArrayList<String>();
//        list.add("student.selectAll");
//        list.add("student.selectOne");
//        list.add("user.add");
//        list.add("user.selectAll");

        //o属性就是刚刚登录绑定的id，通过这个id去数据库查询权限
        Integer id = Integer.parseInt((String) o);
//        List<String> list = userDao.getPermissionListByUserId(StpUtil.getLoginId(id));
        return userDao.getPermissionListByUserId(id);

    }

    //添加某些角色可以访问的
    @Override
    public List<String> getRoleList(Object o, String lognType) {
        //o属性就是刚刚绑定的id，通过这个id去数据库查询权限
        Integer id = Integer.parseInt((String) o);
        User user = userDao.selectOne(new QueryWrapper<User>().select("id,name,password,email").eq("id", id));
        List<String> list = new ArrayList<>();
        //“*”权限表示什么都可以访问
        list.add(user.getName());
        return list;
    }
}