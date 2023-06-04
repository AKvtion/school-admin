SELECT VERSION();

-- 查询所有用户的信息和对应的角色
SELECT user.`id`,user.`username`,user.`password`,user.`status`,user.`email`,user.`type`,role.`name`,role.`description`
 FROM userrole,USER,role WHERE userrole.`userID` = user.`id`AND userrole.`roleID`=role.`id`;
 
SELECT u.id, u.`username`, u.`password`, u.`status`, u.`email`,
               u.`type`, e.`name`,e.`description`
            FROM USER u
      LEFT JOIN userrole ur ON u.`id` = ur.`userID`
      LEFT JOIN role e ON e.`id` = ur.`roleID`;

SELECT u.id, u.`username`, u.`password`, u.`status`, u.`email`,
    u.`type`, r.id,r.`name`,r.`description`
FROM USER u
	 LEFT JOIN userrole ur ON u.id = ur.userID
	 LEFT JOIN role r ON ur.roleID = r.id;
         
 SELECT e.`id`,e.`name`,e.`description`
        FROM USER u
        LEFT JOIN userrole ur ON u.`id` = ur.`userID`
        LEFT JOIN role e ON e.`id` = ur.`roleID` ;
 
-- 在user表中插入数据的同时，向user_role表中插入一条数据

INSERT INTO USER (username, PASSWORD,email,TYPE) 
VALUES ("test", 'e10adc3949ba59abbe56e057f20f883e',"test@163.com","3");

INSERT INTO userrole (userID, roleID) 
SELECT u.id, r.id FROM USER u,role r WHERE u.username = "test" AND r.type = "2";

 
 -- 查询角色拥有的权限
 SELECT role.`name`,role.`description`,permission.`pmsName`,permission.`pmsDescribe`
  FROM role,rolepms,permission 
 WHERE role.`id` = rolepms.`roleID` AND rolepms.`pmsID` = permission.`id`;
 
SELECT r.`name`,r.`description`,r.`status`,p.`id`,p.`pmsName`,p.`pmsDescribe`
            FROM role r
      LEFT JOIN rolepms rp ON rp.`roleID` = r.`id`
      LEFT JOIN permission p ON rp.`pmsID` = p.`id`;
       

                 
SELECT r.`name`,r.`description`,r.`status`,p.`id`,p.`pmsName`,p.`pmsDescribe`
FROM role r
         LEFT JOIN rolepms rp ON rp.`roleID` = r.`id`
         LEFT JOIN permission p ON rp.`pmsID` = p.`id`
GROUP BY r.`id`, p.`id`;


SELECT r.`name`,r.`description`,r.`status`,
       GROUP_CONCAT(DISTINCT p.`pmsName` SEPARATOR ',') AS pmsName,
       GROUP_CONCAT(DISTINCT p.`pmsDescribe` SEPARATOR ',') AS pmsDescribe
FROM role r
LEFT JOIN rolepms rp ON rp.`roleID` = r.`id`
LEFT JOIN permission p ON rp.`pmsID` = p.`id`
GROUP BY r.`id`;


-- 在role表中插入数据的同时，向角色权限表中插入数据

 -- 修改用户的角色 userID 为当前的系统用户，roleID 为前端传过来的变量
UPDATE userrole SET roleID = 2 WHERE userID = 5;

-- 删除用户角色表
DELETE FROM `school`.`userrole` WHERE `userID` = '12'; 
