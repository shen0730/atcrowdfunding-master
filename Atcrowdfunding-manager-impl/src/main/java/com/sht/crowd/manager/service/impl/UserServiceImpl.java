package com.sht.crowd.manager.service.impl;

import com.sht.crowd.bean.User;
import com.sht.crowd.manager.dao.UserMapper;
import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.exception.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserlogin(Map<String, Object> paramMap) {
        User user = userMapper.queryUserlogin(paramMap);
        if(user==null){
            throw new LoginFailException("用户账号或密码错误！");
        }
        return user;
    }
}
