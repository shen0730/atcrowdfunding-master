package com.sht.crowd.manager.service;

import com.sht.crowd.bean.User;
import com.sht.crowd.util.Page;

import java.util.Map;

public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);

    Page queryPage(Integer pageno, Integer pagesize);


}
