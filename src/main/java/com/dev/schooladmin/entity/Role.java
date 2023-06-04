package com.dev.schooladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String name;
    //角色表(管理员 教师 学生用户)
    private String description;

    private String type;
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

