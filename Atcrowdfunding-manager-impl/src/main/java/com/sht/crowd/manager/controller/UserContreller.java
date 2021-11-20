package com.sht.crowd.manager.controller;

import com.sht.crowd.bean.User;
import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.util.AjaxResult;
import com.sht.crowd.util.Msg;
import com.sht.crowd.util.Page;
import com.sht.crowd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserContreller {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String toIndex(){

        return "user/index";
    }

    @RequestMapping("toAdd")
    public String toAdd(){

        return "user/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Integer id,Map map){
        User user = userService.getById(id);
        map.put("user",user);
        return "user/update";
    }

    //修改用户
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.updateUser(user);
            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改数据失败!");
        }

        return result;
    }

    //检查用户名是否可用
    @ResponseBody
    @RequestMapping("/isExistedNickName")
    public Msg checkUsername(@RequestParam("loginacct") String loginacct){
        String regx = "(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        boolean b = userService.selectLoginacct(loginacct);
        if(!loginacct.matches(regx)){
            return Msg.fail().add("va_msg", "用户必须是2-16位数字和字母的组合或者2-5位中文");
        }

        //数据库用户名重复校验
        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg", "用户名被占用！");
        }
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
    @RequestMapping("/doIndex")
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

    //删除用户
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.deleteUser(id);
            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败!");
        }

        return result;
    }

    //批量删除用户
    @ResponseBody
    @RequestMapping("/doDeleteBatch")
    public Object doDeleteBatch(Integer[] id){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.deleteBatchUser(id);
            result.setSuccess(count == id.length);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败!");
        }

        return result;
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
