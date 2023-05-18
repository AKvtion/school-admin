-- 查询用户对应的角色的权限
SELECT user.`id`,user.`username`,user.`password`,user.`status`,user.`type`,role.`name`,role.`description`
 FROM userrole,USER,role WHERE userrole.`userID` = user.`id`AND userrole.`roleID`=role.`id`;
 
 -- 查询角色拥有的权限
 SELECT role.`name`,role.`description`,permission.`pmsName`,permission.`pmsDescribe`
  FROM role,rolepms,permission 
 WHERE role.`id` = rolepms.`roleID` AND rolepms.`pmsID` = permission.`id`;
 
 -- 修改用户的角色 userID 为当前的系统用户，roleID 为前端传过来的变量
UPDATE userrole SET roleID = 2 WHERE userID = 5;