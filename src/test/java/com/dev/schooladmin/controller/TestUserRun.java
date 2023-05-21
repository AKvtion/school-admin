package com.dev.schooladmin.controller;

import com.alibaba.fastjson.JSON;
import com.dev.schooladmin.controller.DTO.UserRole;
import com.dev.schooladmin.controller.DTO.ErpMemberRoles;
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

    @Test
    public void test1(){
        List<UserRole> userRoles = userDao.selectUserRole();
        //���� userRoles �б�
        for (UserRole userRole : userRoles) {
            //����ÿ�� UserRole ���󣬴���һ���µ� ErpMemberRoles ����
            ErpMemberRoles erpMemberRole = new ErpMemberRoles();
            //�Ѳ�ѯ���� UserRole ���������ֵ��ֵ��ErpMemberRoles ��ɫ������
            erpMemberRole.setName(userRole.getName());
            erpMemberRole.setDescription(userRole.getDescription());

            //����һ�� ErpMemberRoles ���͵� List���飬���´����� erpMemberRole ������ӵ��б���
            List<ErpMemberRoles> ers = new ArrayList<>();
            ers.add(erpMemberRole);
            //�� ErpMemberRoles �� List ���û� UserRole ����� erpMemberRoles ������
            userRole.setErpMemberRoles(ers);
        }
        //ʹ��FastJson�� userRoles �б�ת��Ϊ JSON �ַ���
        String s = JSON.toJSONString(userRoles);
        System.out.println(s);
    }
}
