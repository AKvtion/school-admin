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
public class TestUserRoleRun {
    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    //用于测试用户与角色列表之间的关系
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
        String result = JSON.toJSONString(userRoles);
        System.out.println(result);
    }

    //用于测试角色与权限列表之间的关系
    @Test
    public void test2(){
        // 从 roleDao 数据库对象中获取所有角色与权限的关系数据，该数据以 RolePms 对象的形式返回
        List<RolePms> rolePms = roleDao.selectRoleAll();
        // 遍历 rolePms 列表，对于每个角色都创建一个空的 ErpMemberPermission 对象列表
        for (int i = 0; i < rolePms.size(); i++) {
            RolePms r = rolePms.get(i);
            List<ErpMemberPermission> ersList = new ArrayList<>();
            // 再次遍历 rolePms 列表，将每个角色的名称和描述信息封装到一个新的 ErpMemberPermission 对象中,
            // 并将该对象添加到相应的角色的 ErpMemberPermission 对象列表中。
            for (int j = 0; j < rolePms.size(); j++) {
                RolePms role = rolePms.get(j);
                ErpMemberPermission erpMemberPermission = new ErpMemberPermission();
                erpMemberPermission.setPmsName(role.getName());
                erpMemberPermission.setPmsDescribe(role.getPmsDescribe());
                ersList.add(erpMemberPermission);
            }
            r.setErpMemberPermissions(ersList);
        }
        //将处理好的 rolePms 列表转换为 JSON 格式的字符串并输出到控制台
        String result = JSON.toJSONString(rolePms);
        System.out.println(result);
    }
}
