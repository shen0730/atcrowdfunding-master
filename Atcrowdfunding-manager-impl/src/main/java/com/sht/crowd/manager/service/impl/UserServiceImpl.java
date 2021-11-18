package com.sht.crowd.manager.service.impl;

import com.sht.crowd.bean.User;
import com.sht.crowd.exception.LoginFailException;
import com.sht.crowd.manager.dao.UserMapper;
import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.util.Const;
import com.sht.crowd.util.MD5Util;
import com.sht.crowd.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl<Criteria> implements UserService {

    @Autowired
    private UserMapper userMapper;

    //登录
    @Override
    public User queryUserlogin(Map<String, Object> paramMap) {
        User user = userMapper.queryUserlogin(paramMap);
        if(user==null){
            throw new LoginFailException("用户账号或密码错误！");
        }
        return user;
    }

    //分页查询
    @Override
    public Page queryPage(Map<String, Object> paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<User> datas = userMapper.queryList(paramMap);
        page.setDatas(datas);
        Integer totalsize = userMapper.queryCount(paramMap);
        page.setTotalsize(totalsize);
        return page;
    }

    //用户新增
    @Override
    public int save(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = sdf.format(date);
        user.setCreatetime(createtime);
        user.setUserpswd(MD5Util.digest(Const.PASSWORD));
        return userMapper.insert(user);
    }

    //验证用户名是否可用
    @Override
    public boolean selectLoginacct(String loginacct) {
        User user = userMapper.selectLoginacct();
        if(user != null){
            return true;
        }else{
            return false;
        }
    }


//    @Override
//    public Page queryPage(Integer pageno, Integer pagesize) {
//        Page page = new Page(pageno,pagesize);
//        Integer startIndex = page.getStartIndex();
//        List<User> datas = userMapper.queryList(startIndex,pagesize);
//        page.setDatas(datas);
//        Integer totalsize = userMapper.queryCount();
//        page.setTotalsize(totalsize);
//        return page;
//    }



}
