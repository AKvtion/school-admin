package com.dev.schooladmin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.controller.DTO.SignInData;
import com.dev.schooladmin.entity.Class;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.ClassService;
import com.dev.schooladmin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class TestClassRun {
    @Resource
    private ClassService classService;

    //用于测试查询所有班级列表
    @Test
    public void testClass(){
        Page<Class> page = new Page<>(1,3);
        Class colony = new Class();
        Page<Class> pages = this.classService.page(page, new QueryWrapper<>(colony));

        String result = JSON.toJSONString(pages);
        System.out.println(result);
        Assert.equals(-1,result);
    }



}
