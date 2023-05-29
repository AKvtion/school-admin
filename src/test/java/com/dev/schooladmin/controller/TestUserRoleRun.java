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

    //���ڲ����û����ɫ�б�֮��Ĺ�ϵ
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
        String result = JSON.toJSONString(userRoles);
        System.out.println(result);
    }

    //���ڲ��Խ�ɫ��Ȩ���б�֮��Ĺ�ϵ
    @Test
    public void test2(){
        // �� roleDao ���ݿ�����л�ȡ���н�ɫ��Ȩ�޵Ĺ�ϵ���ݣ��������� RolePms �������ʽ����
        List<RolePms> rolePms = roleDao.selectRoleAll();
        // ���� rolePms �б�����ÿ����ɫ������һ���յ� ErpMemberPermission �����б�
        for (int i = 0; i < rolePms.size(); i++) {
            RolePms r = rolePms.get(i);
            List<ErpMemberPermission> ersList = new ArrayList<>();
            // �ٴα��� rolePms �б���ÿ����ɫ�����ƺ�������Ϣ��װ��һ���µ� ErpMemberPermission ������,
            // �����ö�����ӵ���Ӧ�Ľ�ɫ�� ErpMemberPermission �����б��С�
            for (int j = 0; j < rolePms.size(); j++) {
                RolePms role = rolePms.get(j);
                ErpMemberPermission erpMemberPermission = new ErpMemberPermission();
                erpMemberPermission.setPmsName(role.getName());
                erpMemberPermission.setPmsDescribe(role.getPmsDescribe());
                ersList.add(erpMemberPermission);
            }
            r.setErpMemberPermissions(ersList);
        }
        //������õ� rolePms �б�ת��Ϊ JSON ��ʽ���ַ��������������̨
        String result = JSON.toJSONString(rolePms);
        System.out.println(result);
    }
}
