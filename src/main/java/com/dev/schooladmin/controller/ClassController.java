package com.dev.schooladmin.controller;



import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.entity.Class;
import com.dev.schooladmin.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Class)表控制层
 *
 * @author fauchard
 * @since 2023-03-26 20:11:16
 */
@RestController
@RequestMapping("class")
@Api(tags = "班级管理模块")
public class ClassController{
    /**
     * 服务对象
     */
    @Resource
    private ClassService classService;

    /**
     * 分页查询所有数据
     *
     * @param colony 查询实体
     * @return 所有数据
     */
    @GetMapping
    @SaCheckPermission(value = {"class.queryAll"})
    @ApiOperation(value = "查询所有班级信息")
    public Result selectAll(Page<Class> page, Class colony) {
        return new Result().success(this.classService.page(page, new QueryWrapper<>(colony)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // 权限认证：必须具有指定权限才能进入该方法
    @GetMapping("{id}")
    @SaCheckPermission(value = {"class.queryOne"})
    @ApiOperation(value = "查询单条班级信息")
    public Result selectOne(@PathVariable Serializable id) {
        return new Result().success(this.classService.getById(id));
    }

    /**
     * 新增数据
     * 角色认证：必须具有指定角色才能进入该方法
     * @param colony 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @SaCheckRole("admin")
    @ApiOperation(value = "新增班级信息")
    @SaCheckPermission(value = {"class.add"}, mode = SaMode.AND)    //SaMode.AND, 标注一组权限，会话必须全部具有才可通过校验。
    public Result insert(@RequestBody Class colony) {
        return new Result().success(this.classService.save(colony));
    }

    /**
     * 修改数据
     *
     * @param colony 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    @SaCheckPermission("class.update")
    @ApiOperation(value = "修改班级信息")
    public Result update(@RequestBody Class colony) {
        System.out.println(colony.getId());
        return new Result().success(this.classService.updateById(colony));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @SaCheckRole("admin")
    @SaCheckPermission("class.del")
    @ApiOperation(value = "删除班级信息")
    public Result delete(@RequestParam("id") Integer id) {
        return new Result().success(this.classService.removeById(id));
    }
}

