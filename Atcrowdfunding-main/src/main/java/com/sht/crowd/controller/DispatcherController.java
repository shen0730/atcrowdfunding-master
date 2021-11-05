package com.sht.crowd.controller;

import com.sht.crowd.bean.User;
import com.sht.crowd.manager.service.UserService;
import com.sht.crowd.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/main")
    public String main(){

        return "main";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
        Map<String,Object> ParamMap = new HashMap<String, Object>();
        ParamMap.put("loginacct",loginacct);
        ParamMap.put("userpswd",userpswd);
        ParamMap.put("type",type);
        User user = userService.queryUserlogin(ParamMap);
        session.setAttribute(Const.LOGIN_USER,user);
        return "redirect:/main.htm";
    }

//    //异步请求
//    @ResponseBody
//    @RequestMapping("/doLogin")
//    public Object doLogin(String loginacct, String userpswd, String type, @org.jetbrains.annotations.NotNull HttpSession session){
//
//        AjaxResult result= new AjaxResult();
//
//        try {
//            Map<String,Object> ParamMap = new HashMap<String, Object>();
//            ParamMap.put("loginacct",loginacct);
//            ParamMap.put("userpswd",userpswd);
//            ParamMap.put("type",type);
//            User user = userService.queryUserlogin(ParamMap);
//            session.setAttribute(Const.LOGIN_USER,user);
//            result.setSuccess(true);
//        }catch (Exception e){
//            result.setMessage("登录失败！");
//            e.printStackTrace();
//            result.setSuccess(false);
//        }
//
//        return result;
//    }
}
