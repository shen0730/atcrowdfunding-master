package com.sht.crowd.manager.service;

import com.sht.crowd.bean.User;
import com.sht.crowd.util.Page;

import java.util.Map;

public interface UserService {

    User queryUserlogin(Map<String, Object> paramMap);

    //@Deprecated
    //Page queryPage(Integer pageno, Integer pagesize);


    Page queryPage(Map<String, Object> paramMap);

    int save(User user);

    boolean selectLoginacct(String loginacct);
}
