package com.sht.crowd.manager.controller;

import com.sht.crowd.manager.service.AdvertService;
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
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/index")
    public String index(){

        return "advert/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "advert/add";
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
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);

            if (StringUtil.isNotEmpty(queryText)){
                if (queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                paramMap.put("queryText", queryText);
            }
            Page page = advertService.queryPage(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }

}
