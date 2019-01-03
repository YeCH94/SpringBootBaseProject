package com.chj.bootbase.controller;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.dto.MemberRequestDto;
import com.chj.bootbase.service.CustomMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    private CustomMemberService customMemberService;

    @ModelAttribute("Member")
    public MemberRequestDto memberRequestDto(){
        return new MemberRequestDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping
    public String registerMember(@ModelAttribute("Member") @Valid MemberRequestDto memberRequestDto, BindingResult result){
        Member existing = customMemberService.findByEmail(memberRequestDto.getEmail());

        if(existing != null){
            result.rejectValue("email", null, "There is already an registered with that E-mail");
        }
        if(result.hasErrors()){
            return "register";
        }

        customMemberService.save(memberRequestDto);
        return "redirect:/register?success";
    }
}
