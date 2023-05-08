package com.dev.schooladmin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.schooladmin.base.entity.Result;
import com.dev.schooladmin.entity.Notice;
import com.dev.schooladmin.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Notice)表控制层
 *
 * @author fauchard
 * @since 2023-04-08 10:45:02
 */
@RestController
@RequestMapping("notice")
@Api(tags = "公告通知模块")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param notice 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    @SaCheckPermission("notice.query")
    @ApiOperation(value = "查询所有通知消息")
    public Result selectAll(Page<Notice> page, Notice notice) {
        return new Result().success(this.noticeService.page(page, new QueryWrapper<>(notice)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    @SaCheckPermission("notice.queryOne")
    @ApiOperation(value = "查询单条通知消息")
    public Result selectOne(@PathVariable Serializable id) {
        return new Result().success(this.noticeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param notice 实体对象
     * @return 新增结果
     */
    @PostMapping
    @SaCheckRole("notice.add")
    @ApiOperation(value = "新增通知消息")
    public Result insert(@RequestBody Notice notice) {
        return new Result().success(this.noticeService.save(notice));
    }

    /**
     * 修改数据
     *
     * @param notice 实体对象
     * @return 修改结果
     */
    @PutMapping
    @SaCheckPermission("notice.update")
    @ApiOperation(value = "修改通知消息")
    public Result update(@RequestBody Notice notice) {
        return new Result().success(this.noticeService.updateById(notice));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @SaCheckPermission("notice.del")
    @ApiOperation(value = "删除通知消息")
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return new Result().success(this.noticeService.removeByIds(idList));
    }
}

