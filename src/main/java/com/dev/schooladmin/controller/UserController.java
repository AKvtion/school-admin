package com.dev.schooladmin.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.controller.DTO.SignInData;
import com.dev.schooladmin.entity.User;
import com.dev.schooladmin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
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
@Api(tags = "UserController")
@RequestMapping("user")
public class UserController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 登录方法
     * @param data
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody SignInData data){
        if (Validator.isEmpty(data.getName()) || Validator.isEmpty(data.getPassword())){
            return new Result().fail(400,"用户名或密码不能为空！");
        }
        SaTokenInfo saTokenInfo = userService.login(data);
        if ( saTokenInfo == null ){
            return new Result().fail(400,"用户名或密码错误！");
        }
        //用map集合将登录生成的token信息返回给前端
        Map<String,Object> maps = new HashMap<>();
        maps.put("token",saTokenInfo.getTokenValue());
        maps.put("roleList",StpUtil.getRoleList());
        maps.put("permissionList",StpUtil.getPermissionList());
        return new Result().success(maps);
    }

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
    @GetMapping("/selectAll")
    @SaCheckLogin
    @SaCheckRole("admin")
    @SaCheckPermission(value = {"user.query"}, mode = SaMode.AND)
    public Result selectAll(Page<User> page, User user) {
        return new Result().success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id
     * @return
     */
    @SaCheckLogin
    @GetMapping("{id}")
    @SaCheckPermission("user.queryOne")
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
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.userService.removeByIds(idList));
    }
}

