package com.sht.crowd.manager.service;

import com.sht.crowd.util.Page;
import com.sht.crowd.vo.Data;

import java.util.Map;

public interface RoleService {
    Page pageQuery(Map paramMap);

    int saveRolePermissionRelationship(Integer roleId, Data data);

    int deleteRole(Integer id);
}
