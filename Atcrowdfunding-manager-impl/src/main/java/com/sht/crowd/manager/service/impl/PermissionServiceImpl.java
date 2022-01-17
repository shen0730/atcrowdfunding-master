package com.sht.crowd.manager.service.impl;

import com.sht.crowd.bean.Permission;
import com.sht.crowd.manager.dao.PermissionMapper;
import com.sht.crowd.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission getRootPermission() {

        return permissionMapper.getRootPermission();
    }

    @Override
    public List<Permission> getChildrenPerminssionByPid(Integer id) {
        return permissionMapper.getChildrenPermissionByPid(id);
    }

}
