package com.dev.schooladmin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.dev.schooladmin.controller.DTO.*;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class TestLoginRun {
    @Resource
    private UserService userService;

    //���ڲ����û���¼����
    @Test
    public void test1(){
        // �½�һ�� SignInData ���� data
        SignInData data = new SignInData();
        // �������е� username �� password ���ԣ����ڴ���û�����ĵ�¼��Ϣ��
        data.setUsername("admin");
        data.setPassword("123456");
        // ���� Validator ����е� isEmpty() �����ж��û�������û����������Ƿ�Ϊ�գ����Ϊ���򷵻ش�����Ϣ��
        if (Validator.isEmpty(data.getUsername()) || Validator.isEmpty(data.getPassword())){
            System.out.println("400,�û��������벻��Ϊ�գ�");
        }
        // ���� userService �е� login() ���������û�����ĵ�¼��Ϣ���������֤��
        // ����ȡ���ص� SaTokenInfo ���󣬸ö�������˵�¼�ɹ������ɵ� token ����Ϣ
        SaTokenInfo saTokenInfo = userService.login(data);
        if ( saTokenInfo == null ){
            //�����ȡ���� saTokenInfo Ϊ null�����ʾ��֤ʧ�ܣ����������Ϣ��
            System.out.println("400,�û������������");
        }
        // �� token ֵ��װ��һ�� maps ��ϣ���У��ٽ��ù�ϣ��ת��Ϊ JSON ��ʽ���ַ��������������̨
        Map<String,Object> maps = new HashMap<>();
        assert saTokenInfo != null;
        maps.put("token",saTokenInfo.getTokenValue());
        String result = JSON.toJSONString(maps);
        System.out.println(result);
    }

    // ʹ���� sa-token ��ܽ��������֤���������û��������Ϣ
    @Test
    public void test2(){
        SignInData data = new SignInData();
        // �������е� username �� password ���ԣ����ڴ���û�����ĵ�¼��Ϣ��
        data.setUsername("admin");
        // ��map���Ͻ���¼���ɵ�token��Ϣ���ظ�ǰ��
        Map<String,Object> maps = new HashMap<>();
        // ���� userService �е� selectOne() �����������û�����ѯ���ݿ����Ƿ���ڶ�Ӧ���û���¼������û������ڣ������������ʾ��Ϣ��
        User user = userService.selectOne(data.getUsername());
        if (user == null){
            System.out.println("400,�û������������");
        }
        assert user != null;
        // ����û����ڣ���Ӹ��û���¼�л�ȡͷ�������������Ϣ��������洢��һ����Ϊ maps �Ĺ�ϣ���С�
        maps.put("avatar",user.getAvatar());
        maps.put("name",user.getUsername());
        // ���� sa-token ����ṩ StpUtil ���е� getRoleList() �� getPermissionList() ��������ȡ��ǰ�û��Ľ�ɫ�б��Ȩ���б�
        maps.put("roles", StpUtil.getRoleList());
        maps.put("data",StpUtil.getPermissionList());
        String result = JSON.toJSONString(maps);
        System.out.println(result);
    }

}
