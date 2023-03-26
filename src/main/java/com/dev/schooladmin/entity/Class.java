package com.dev.schooladmin.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (Class)表实体类
 *
 * @author fauchard
 * @since 2023-03-26 20:11:16
 */
@Data
@SuppressWarnings("serial")
public class Class extends Model<Class> {
    //主键
    private Integer id;
    //班级名
    private String name;
    //年级
    private String grade;
    //专业
    private String major;


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

