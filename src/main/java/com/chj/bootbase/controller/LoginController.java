package com.chj.bootbase.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(){

        return "login";
    }
}
