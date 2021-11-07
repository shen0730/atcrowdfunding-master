package com.sht.crowd.manager.dao;

import com.sht.crowd.bean.Permission;
import com.sht.crowd.bean.Role;
import com.sht.crowd.bean.User;
import com.sht.crowd.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

    List<User> queryList(@Param("startIndex") Integer startIndex, @Param("pagesize") Integer pagesize);

    Integer queryCount();

    //List<User> queryList(Map<String, Object> paramMap);

    //Integer queryCount(Map<String, Object> paramMap);

    //int deleteBatchUserByVo(Data data);

    int deleteBatchUserByVo(List<User> userList);

    List<Role> queryAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    int saveUserRoleRelationship(@Param("userid") Integer userid, @Param("data") Data data);

    int deleteUserRoleRelationship(@Param("userid") Integer userid, @Param("data") Data data);

    List<Permission> queryPermissionByUserid(Integer id);

}