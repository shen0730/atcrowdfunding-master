package com.sht.crowd.manager.service;

import com.sht.crowd.bean.Permission;

import java.util.List;

public interface PermissionService {

    List<Integer> queryPermissionidsByRoleid(Integer roleId);

    Permission getRootPermission();

    List<Permission> getChildrenPerminssionByPid(Integer id);

    List<Permission> queryAllPermission();

    int savePermission(Permission permission);

    Permission getPermissionById(Integer id);

    int updatePermission(Permission permission);

    int deletePermission(Integer id);
}
