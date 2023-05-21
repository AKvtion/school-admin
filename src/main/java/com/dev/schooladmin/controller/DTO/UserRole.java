package com.dev.schooladmin.controller.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserRole {
    //用户的属性
    private int id;
    private String username;
    private String password;
    private Integer status;
    private String email;
    private String type;

    //角色的属性
    private String name;
    private String description;
    //封装返回数据
    private List<ErpMemberRoles> erpMemberRoles;
}
