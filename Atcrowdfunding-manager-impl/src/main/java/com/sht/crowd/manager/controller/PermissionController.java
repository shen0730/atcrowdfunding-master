package com.sht.crowd.manager.controller;

import com.sht.crowd.bean.Permission;
import com.sht.crowd.manager.service.PermissionService;
import com.sht.crowd.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){

        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        AjaxResult result = new AjaxResult();
        try {

            List<Permission> root = new ArrayList<Permission>();

            //父
            Permission permission = permissionService.getRootPermission();
            root.add(permission);

            //子
            List<Permission> children = permissionService.getChildrenPerminssionByPid(permission.getId());


            //设置父子关系
            permission.setChildren(children);

            for (Permission child : children){
                child.setOpen(true);
                List<Permission> innerChildren = permissionService.getChildrenPerminssionByPid(child.getId());
                child.setChildren(innerChildren);
            }
            result.setSuccess(true);
            result.setDate(root);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("加载许可树数据失败！");
        }
        return result;
    }
}
