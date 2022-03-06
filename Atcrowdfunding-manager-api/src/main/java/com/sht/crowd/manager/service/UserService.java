package com.sht.crowd.manager.service;

import com.sht.crowd.bean.Permission;
import com.sht.crowd.bean.Role;
import com.sht.crowd.bean.User;
import com.sht.crowd.util.Page;
import com.sht.crowd.vo.Data;

import java.util.List;
import java.util.Map;

public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);

    Page queryPage(Map<String, Object> paramMap);

    int save(User user);

    boolean selectLoginacct(String loginacct);

    User getById(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

    int deleteBatchUser(Integer[] ids);

    List<Role> queryAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    int saveUserRoleRelationship(Integer userid, Data data);

    int deleteUserRoleRelationship(Integer userid, Data data);

    List<Permission> queryPermissionByUserid(Integer id);
}
