package com.sht.crowd.manager.controller;

import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.util.AjaxResult;
import com.sht.crowd.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserContreller {

    @Autowired
    private UserService userService;

    @RequestMapping("toIndex")
    public String toIndex(){

        return "user/index";
    }

    //异步请求
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize){
        AjaxResult result = new AjaxResult();
        try {
            Page page = userService.queryPage(pageno, pagesize);
            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }


//    //同步请求
//    @RequestMapping("/index")
//    public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,  Map map){
//        Page page = userService.queryPage(pageno,pagesize);
//        map.put("page",page);
//        return "user/index";
//    }
}
