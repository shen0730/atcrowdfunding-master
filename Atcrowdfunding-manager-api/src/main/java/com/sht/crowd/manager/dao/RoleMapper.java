package com.sht.crowd.manager.dao;

import com.sht.crowd.bean.Role;
import com.sht.crowd.bean.RolePermission;
import com.sht.crowd.bean.User;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<User> pageQuery(Map paramMap);

    Integer queryCount(Map paramMap);

    void deleteRolePermissionRelationship(Integer roleid);

    int insertRolePermission(RolePermission rp);
}