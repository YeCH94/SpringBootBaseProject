package com.chj.bootbase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/login")
    public String home(Model model, Principal principal){
        model.addAttribute("auth", principal);
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(){
        return "redirect:/login";
    }
}
