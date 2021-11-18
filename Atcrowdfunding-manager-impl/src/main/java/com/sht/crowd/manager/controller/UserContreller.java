package com.sht.crowd.manager.controller;

import com.sht.crowd.bean.User;
import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.util.AjaxResult;
import com.sht.crowd.util.Page;
import com.sht.crowd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserContreller {

    @Autowired
    private UserService userService;

    @RequestMapping("toIndex")
    public String toIndex(){

        return "user/index";
    }

    @RequestMapping("toAdd")
    public String toAdd(){

        return "user/add";
    }



    //新增用户
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.save(user);
            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败!");
        }

        return result;
    }


    //条件查询
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize,
                        String queryText){
        AjaxResult result = new AjaxResult();
        try {
            Map paramMap = new HashMap();
            paramMap.put("pageno",pageno);
            paramMap.put("pagesize",pagesize);

            if(StringUtil.isNotEmpty(queryText)){
                if (queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                paramMap.put("queryText",queryText);
            }
            Page page = userService.queryPage(paramMap);
            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }


    //异步请求
//    @ResponseBody
//    @RequestMapping("/index")
//    public Object index(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize){
//        AjaxResult result = new AjaxResult();
//        try {
//            Page page = userService.queryPage(pageno, pagesize);
//            result.setSuccess(true);
//            result.setPage(page);
//        } catch (Exception e) {
//            result.setSuccess(false);
//            e.printStackTrace();
//            result.setMessage("查询数据失败!");
//        }
//
//        return result; //将对象序列化为JSON字符串,以流的形式返回.
//    }


//    //同步请求
//    @RequestMapping("/index")
//    public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,  Map map){
//        Page page = userService.queryPage(pageno,pagesize);
//        map.put("page",page);
//        return "user/index";
//    }
}
