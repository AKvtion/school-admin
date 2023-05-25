package com.dev.schooladmin.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.controller.DTO.SignInData;
import com.dev.schooladmin.controller.DTO.UserRole;
import com.dev.schooladmin.controller.DTO.ErpMemberRoles;
import com.dev.schooladmin.dao.UserDao;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author fauchard
 * @since 2023-03-25 16:35:53
 */
@RestController
@Api(tags = "管理员模块")
@RequestMapping("user")
public class UserController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    static String name;

    // 查询权限   ---- http://localhost:8081/user/getPermission

    /**
     * 当前登录账号拥有的所有权限
     * @return SaResult
     */
    @RequestMapping("getPermission")
    @ApiOperation(value = "当前登录账号拥有的所有权限")
    public SaResult getPermission() {
        // 查询权限信息 ，如果当前会话未登录，会返回一个空集合
        List<String> permissionList = StpUtil.getPermissionList();
//        System.out.println("当前登录账号拥有的所有权限：" + permissionList);
        // 查询角色信息 ，如果当前会话未登录，会返回一个空集合
        List<String> roleList = StpUtil.getRoleList();
//        System.out.println("当前登录账号拥有的所有角色：" + roleList);
        // 返回给前端
        return SaResult.ok()
                .set("roleList", roleList)
                .set("permissionList", permissionList);
    }

    /**
     * 登录方法
     * @param data 实体
     * @return Result
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录方法")
    public Result login(@RequestBody SignInData data){
        if (Validator.isEmpty(data.getUsername()) || Validator.isEmpty(data.getPassword())){
            return new Result().fail(400,"用户名或密码不能为空！");
        }
        SaTokenInfo saTokenInfo = userService.login(data);
        if ( saTokenInfo == null ){
            return new Result().fail(400,"用户名或密码错误！");
        }
        name = data.getUsername();
        //用map集合将登录生成的token信息返回给前端
        Map<String,Object> maps = new HashMap<>();
        maps.put("token",saTokenInfo.getTokenValue());
        return new Result().success(maps);
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户信息")
    public Result info(){
        //用map集合将登录生成的token信息返回给前端
        Map<String,Object> maps = new HashMap<>();
        maps.put("avatar","https://s2.loli.net/2022/08/18/mXaLtIijAKPq4D1.png");
        User user = userService.selectOne(name);
        if (user == null){
            return new Result().fail("获取用户信息失败",400);
        }
        maps.put("name",user.getUsername());
        maps.put("roles",StpUtil.getRoleList());
        maps.put("data",StpUtil.getPermissionList());
        return new Result().success(maps);
    }

    /**
     * 查询当前登录状态
     * @return Result
     */
    @ApiOperation(value = "查询当前登录状态")
    @GetMapping(value = "/isLogin")
    @ResponseBody
    public Result isLogin() {
        return new Result().success(StpUtil.isLogin());
    }

    /**
     * 退出方法
     */
    @PostMapping("/loginOut")
    @ApiOperation(value = "退出方法")
    public Result loginOut(){
        StpUtil.logout();
        return new Result().success();
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @SaCheckLogin
    @SaCheckRole("admin")
    @GetMapping("/list")
    @ApiOperation(value = "查询所有用户的信息和对应的角色")
    @SaCheckPermission(value = {"user.query"}, mode = SaMode.AND)
    public Result selectAll(Page<User> page, User user) {
        List<UserRole> userRoles = this.userService.selectUserRole();
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
        //返回 userRoles 数据
        return new Result().success(userRoles);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return Result
     */
    @SaCheckLogin
    @GetMapping("{id}")
    @SaCheckPermission("user.queryOne")
    @ApiOperation(value = "查询单条管理员信息")
    public Result selectOne(@PathVariable Serializable id) {
        return new Result().success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @SaCheckLogin
    @PostMapping
    @SaCheckPermission("user.add")
    @ApiOperation(value = "新增管理员信息")
    public Result insert(@RequestBody User user) {
        return new Result().success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @SaCheckLogin
    @PutMapping
    @SaCheckPermission("user.update")
    @ApiOperation(value = "修改管理员信息")
    public Result update(@RequestBody User user) {
        return new Result().success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @SaCheckLogin
    @DeleteMapping
    @SaCheckPermission("user.del")
    @ApiOperation(value = "删除管理员信息")
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.userService.removeByIds(idList));
    }
}

