package com.chj.bootbase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout){
        ModelAndView mav = new ModelAndView();
        if(error != null){
            mav.addObject("error", "Invalid username of password");
        }
        if(logout != null){
            mav.addObject("msg", "You have been logged out");
        }
        mav.setViewName("login");
        return mav;
    }

}
