package com.sht.crowd.manager.controller;

import com.sht.crowd.bean.Permission;
import com.sht.crowd.manager.service.PermissionService;
import com.sht.crowd.manager.service.RoleService;
import com.sht.crowd.util.AjaxResult;
import com.sht.crowd.util.Page;
import com.sht.crowd.util.StringUtil;
import com.sht.crowd.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }

    //条件查询
    @ResponseBody
    @RequestMapping("/pageQuery")
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
            Page page = roleService.pageQuery(paramMap);
            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }


    @RequestMapping("/assignPermission")
    public String assignPermission(){
        return "role/assignPermission";
    }

    //分配角色许可
    @ResponseBody
    @RequestMapping("doAssignPermission")
    public Object doassignPermission(Integer roleid, Data data){
        AjaxResult result = new AjaxResult();
        try {
            int count = roleService.saveRolePermissionRelationship(roleid,data);
            result.setSuccess(count == data.getIds().size());
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("分配角色数据失败！");
        }
        return result;
    }

    //查询分配许可
    @ResponseBody
    @RequestMapping("/loadDataAsync")
    public Object loadDataAsync(Integer roleid){
        List<Permission> root = new ArrayList<Permission>();
        List<Permission> childredPermissons =  permissionService.queryAllPermission();

        //根据角色id查询该角色之前所分配过的许可
        List<Integer> permissonIdsForRoleid = permissionService.queryPermissionidsByRoleid(roleid);
        Map<Integer, Permission> map = new HashMap<Integer, Permission>();
        for (Permission innerpermission : childredPermissons){
            map.put(innerpermission.getId(), innerpermission);
            if (permissonIdsForRoleid.contains(innerpermission.getId())){
                innerpermission.setChecked(true);
            }
        }
        for (Permission permission : childredPermissons){
            //通过子查找父
            //子菜单
            Permission child = permission;//假设为子菜单
            if(child.getPid() == null){
                root.add(permission);
            }else{
                //父节点
                Permission parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return root;
    }

    //删除角色
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id){
        AjaxResult result = new AjaxResult();
        try {
            int count = roleService.deleteRole(id);

            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }


}
