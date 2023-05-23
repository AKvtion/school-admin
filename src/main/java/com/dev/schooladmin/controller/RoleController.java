package com.dev.schooladmin.controller;



import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.controller.DTO.ErpMemberPermission;
import com.dev.schooladmin.controller.DTO.RolePms;
import com.dev.schooladmin.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author fauchard
 * @since 2023-05-21 15:46:30
 */
@RestController
@Api(tags = "角色模块")
@RequestMapping("role")
public class RoleController{
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @SaCheckLogin
    @SaCheckRole("admin")
    @GetMapping("/list")
    @ApiOperation(value = "查询所有角色的信息和对应的权限")
    public Result selectAll(Page<com.dev.schooladmin.entity.Role> page, com.dev.schooladmin.entity.Role role) {
//        return new Result().success(this.roleService.page(page, new QueryWrapper<>(role)));
        List<RolePms> rolePms = this.roleService.selectRoleAll();
        for (int i = 0; i < rolePms.size(); i++) {
            RolePms r = rolePms.get(i);
            List<ErpMemberPermission> ersList = new ArrayList<>();
            for (int j = 0; j < rolePms.size(); j++) {
                RolePms rolePms1 = rolePms.get(j);
                ErpMemberPermission erpMemberPermission = new ErpMemberPermission();
                erpMemberPermission.setPmsName(rolePms1.getName());
                erpMemberPermission.setPmsDescribe(rolePms1.getPmsDescribe());
                ersList.add(erpMemberPermission);
            }
            r.setErpMemberPermissions(ersList);
        }
//        for (RolePms rolePms : roleList) {
//            ErpMemberPermission emp = new ErpMemberPermission();
//            emp.setPmsName(rolePms.getName());
//            emp.setPmsDescribe(rolePms.getDescription());
//
//            List<ErpMemberPermission> empList = new ArrayList<>();
//            empList.add(emp);
//            rolePms.setErpMemberPermissions(empList);
//        }
        return new Result().success(rolePms);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return new Result().success(this.roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody com.dev.schooladmin.entity.Role role) {
        return new Result().success(this.roleService.save(role));
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody com.dev.schooladmin.entity.Role role) {
        return new Result().success(this.roleService.updateById(role));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.roleService.removeByIds(idList));
    }
}

