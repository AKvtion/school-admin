package com.dev.schooladmin.controller;

import com.alibaba.fastjson.JSON;
import com.dev.schooladmin.controller.DTO.*;
import com.dev.schooladmin.dao.RoleDao;
import com.dev.schooladmin.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestUserRun {
    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Test
    public void test1(){
        List<UserRole> userRoles = userDao.selectUserRole();
        //遍历 userRoles 列表
        for (UserRole userRole : userRoles) {
            //对于每个 UserRole 对象，创建一个新的 ErpMemberRoles 对象
            ErpMemberRoles erpMemberRole = new ErpMemberRoles();
            //把查询出来 UserRole 对象的属性值赋值到ErpMemberRoles 角色类属性
            erpMemberRole.setName(userRole.getName());
            erpMemberRole.setDescription(userRole.getDescription());

            //创建一个 ErpMemberRoles 类型的 List数组，将新创建的 erpMemberRole 对象添加到列表中
            List<ErpMemberRoles> ers = new ArrayList<>();
            ers.add(erpMemberRole);
            //将 ErpMemberRoles 的 List 设置回 UserRole 对象的 erpMemberRoles 属性中
            userRole.setErpMemberRoles(ers);
        }
        //使用FastJson将 userRoles 列表转换为 JSON 字符串
        String s = JSON.toJSONString(userRoles);
        System.out.println(s);
    }

    //角色表和权限表
    @Test
    public void test2(){
        List<RolePms> rolePms = roleDao.selectRoleAll();
        for (int i = 0; i < rolePms.size(); i++) {
            RolePms r = rolePms.get(i);

            List<ErpMemberPermission> ersList = new ArrayList<>();

            for (int j = 0; j < rolePms.size(); j++) {
                RolePms role = rolePms.get(j);
                ErpMemberPermission erpMemberPermission = new ErpMemberPermission();

                erpMemberPermission.setPmsName(role.getName());
                erpMemberPermission.setPmsDescribe(role.getPmsDescribe());
                ersList.add(erpMemberPermission);
            }
            r.setErpMemberPermissions(ersList);
        }
        String s = JSON.toJSONString(rolePms);
        System.out.println(s);
    }
}
