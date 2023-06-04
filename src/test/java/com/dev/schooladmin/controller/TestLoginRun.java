package com.dev.schooladmin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
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

    //用于测试用户登录功能
    @Test
    public void testLogin(){
        // 新建一个 SignInData 对象 data
        SignInData data = new SignInData();
        // 设置其中的 username 和 password 属性，用于存放用户输入的登录信息。
        data.setUsername("admin");
        data.setPassword("123456");
        // 利用 Validator 类库中的 isEmpty() 方法判断用户输入的用户名和密码是否为空，如果为空则返回错误信息。
        if (Validator.isEmpty(data.getUsername()) || Validator.isEmpty(data.getPassword())){
            System.out.println("400,用户名或密码不能为空！");
        }
        // 调用 userService 中的 login() 方法，将用户输入的登录信息传入进行验证，
        // 并获取返回的 SaTokenInfo 对象，该对象包含了登录成功后生成的 token 等信息
        SaTokenInfo saTokenInfo = userService.login(data);
        if ( saTokenInfo == null ){
            //如果获取到的 saTokenInfo 为 null，则表示验证失败，输出错误信息。
            System.out.println("400,用户名或密码错误！");
        }
        // 将 token 值封装到一个 maps 哈希表中，再将该哈希表转换为 JSON 格式的字符串并输出到控制台
        Map<String,Object> maps = new HashMap<>();
        assert saTokenInfo != null;
        maps.put("token",saTokenInfo.getTokenValue());
        String result = JSON.toJSONString(maps);
        System.out.println(result);
    }

    // 使用了 sa-token 框架进行身份验证，并返回用户的相关信息
    @Test
    public void testUserInfo(){
        SignInData data = new SignInData();
        // 设置其中的 username 和 password 属性，用于存放用户输入的登录信息。
        data.setUsername("admin");
        // 用map集合将登录生成的token信息返回给前端
        Map<String,Object> maps = new HashMap<>();
        // 调用 userService 中的 selectOne() 方法，根据用户名查询数据库中是否存在对应的用户记录。如果用户不存在，则输出错误提示信息。
        User user = userService.selectOne(data.getUsername());
        if (user == null){
            System.out.println("400,用户名或密码错误！");
        }
        assert user != null;
        // 如果用户存在，则从该用户记录中获取头像、姓名等相关信息，并将其存储到一个名为 maps 的哈希表中。
        maps.put("avatar",user.getAvatar());
        maps.put("name",user.getUsername());
        // 调用 sa-token 框架提供 StpUtil 类中的 getRoleList() 和 getPermissionList() 方法，获取当前用户的角色列表和权限列表
        maps.put("roles", StpUtil.getRoleList());
        maps.put("data",StpUtil.getPermissionList());
        String result = JSON.toJSONString(maps);
        System.out.println(result);
        Assert.notNull(result);
    }

}
