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
    public List<Integer> queryPermissionidsByRoleid(Integer roleId) {
        return permissionMapper.queryPermissionidsByRoleid(roleId);
    }

    @Override
    public Permission getRootPermission() {

        return permissionMapper.getRootPermission();
    }

    @Override
    public List<Permission> getChildrenPerminssionByPid(Integer id) {
        return permissionMapper.getChildrenPermissionByPid(id);
    }

    @Override
    public List<Permission> queryAllPermission() {
        return permissionMapper.queryAllPermission();
    }

    @Override
    public int savePermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public int deletePermission(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

}
