package com.dev.schooladmin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (Notice)表实体类
 *
 * @author fauchard
 * @since 2023-04-08 10:45:03
 */
@SuppressWarnings("serial")
@Data
public class Notice extends Model<Notice> {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //发布者
    private Integer publisherId;
    //发布时间
    private Date createTime;
    //修改时间
    private Date updateTime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    }

