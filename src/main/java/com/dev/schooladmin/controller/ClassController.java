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
    // 查询权限   ---- http://localhost:8081/class/getPermission
    @RequestMapping("getPermission")
    public SaResult getPermission() {
        // 查询权限信息 ，如果当前会话未登录，会返回一个空集合
        List<String> permissionList = StpUtil.getPermissionList();
        System.out.println("当前登录账号拥有的所有权限：" + permissionList);

        // 查询角色信息 ，如果当前会话未登录，会返回一个空集合
        List<String> roleList = StpUtil.getRoleList();
        System.out.println("当前登录账号拥有的所有角色：" + roleList);
        // 返回给前端
        return SaResult.ok()
                .set("roleList", roleList)
                .set("permissionList", permissionList);
    }
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param classs 查询实体
     * @return 所有数据
     */
    @GetMapping
    @SaCheckPermission(value = {"class.queryAll"})
    public Result selectAll(Page<Class> page, Class classs) {
        return new Result().success(this.classService.page(page, new QueryWrapper<>(classs)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // 权限认证：必须具有指定权限才能进入该方法
    @SaCheckPermission(value = {"class.queryOne"})
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
    // 角色认证：必须具有指定角色才能进入该方法
    @SaCheckRole("admin")
    @SaCheckPermission(value = {"class.add"}, mode = SaMode.AND)    //SaMode.AND, 标注一组权限，会话必须全部具有才可通过校验。
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
    @SaCheckPermission("class_update")
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
    @SaCheckPermission("class.del")
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.classService.removeByIds(idList));
    }
}

