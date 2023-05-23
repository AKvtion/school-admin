package com.dev.schooladmin.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (Role)表实体类
 *
 * @author fauchard
 * @since 2023-05-21 15:46:30
 */
@SuppressWarnings("serial")
@Data
public class Role extends Model<Role> {
    
    private Integer id;
    
    private String name;
    //角色表(超级管理员 管理员 用户)
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

