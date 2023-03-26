package com.dev.schooladmin.controller;



import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.entity.Class;
import com.dev.schooladmin.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Class)表控制层
 *
 * @author fauchard
 * @since 2023-03-26 20:11:16
 */
@RestController
@RequestMapping("class")
public class ClassController{
    /**
     * 服务对象
     */
    @Resource
    private ClassService classService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param classs 查询实体
     * @return 所有数据
     */
    @GetMapping
    // 角色认证：必须具有指定角色才能进入该方法
    @SaCheckRole("teacher")
    public Result selectAll(Page<Class> page, Class classs) {
        return new Result().success(this.classService.page(page, new QueryWrapper<>(classs)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @SaCheckRole("student")
    // 权限认证：必须具有指定权限才能进入该方法
    @SaCheckPermission("user.selectOne")
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return new Result().success(this.classService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param classs 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody Class classs) {
        return new Result().success(this.classService.save(classs));
    }

    /**
     * 修改数据
     *
     * @param classs 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody Class classs) {
        return new Result().success(this.classService.updateById(classs));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.classService.removeByIds(idList));
    }
}

