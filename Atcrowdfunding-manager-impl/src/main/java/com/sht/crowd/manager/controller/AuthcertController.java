package com.sht.crowd.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth_adv")
public class AuthcertController {

    @RequestMapping("/index")
    public String index(){

        return "index";
    }
}
