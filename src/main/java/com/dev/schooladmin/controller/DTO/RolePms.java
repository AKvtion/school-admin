package com.dev.schooladmin.controller.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RolePms {
    private int id;
    private String name;
    private String description;
    private int status;

    //角色属性
    private String pmsName;
    private String pmsDescribe;

    private List<ErpMemberPermission> erpMemberPermissions;

}
