package com.sht.crowd.manager.service;

import com.sht.crowd.bean.User;

import java.util.Map;

public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);
}
