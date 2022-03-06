package com.sht.crowd.manager.service.impl;

import com.sht.crowd.bean.RolePermission;
import com.sht.crowd.bean.User;
import com.sht.crowd.manager.dao.RoleMapper;
import com.sht.crowd.manager.service.RoleService;
import com.sht.crowd.util.Page;
import com.sht.crowd.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page pageQuery(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<User> datas = roleMapper.pageQuery(paramMap);
        page.setDatas(datas);
        Integer totalsize = roleMapper.queryCount(paramMap);
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int saveRolePermissionRelationship(Integer roleid, Data data) {
        roleMapper.deleteRolePermissionRelationship(roleid);
        int totalCount = 0;
        List<Integer> ids = data.getIds();
        for(Integer permissionid : ids){
            RolePermission rp = new RolePermission();
            rp.setRoleid(roleid);
            rp.setPermissionid(permissionid);
            int count = roleMapper.insertRolePermission(rp);
            totalCount += count;
        }
        return totalCount;
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
