package com.dev.schooladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author fauchard
 * @since 2023-03-25 16:35:58
 */
@Data
@SuppressWarnings("serial")
public class User extends Model<User> {

    //主键
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    //头像
    private String avatar;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //状态 用户状态 0表示禁用，1表示启用
    private Integer status;
    //类型
    private String type;
    //标识
    private Integer user_id;
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

