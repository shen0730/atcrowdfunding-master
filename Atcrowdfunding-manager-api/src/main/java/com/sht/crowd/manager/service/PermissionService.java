package com.sht.crowd.manager.service;

import com.sht.crowd.bean.Permission;

import java.util.List;

public interface PermissionService {
    Permission getRootPermission();

    List<Permission> getChildrenPerminssionByPid(Integer id);
}
