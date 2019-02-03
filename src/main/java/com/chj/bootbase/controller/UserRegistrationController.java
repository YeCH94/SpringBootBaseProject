package com.chj.bootbase.controller;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.dto.MemberRequestDto;
import com.chj.bootbase.error.AlreadyExistException;
import com.chj.bootbase.error.BadRequestException;
import com.chj.bootbase.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    private MemberServiceImpl memberServiceImpl;

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
        Member existing = memberServiceImpl.findByEmail(memberRequestDto.getEmail());
        if(result.hasErrors()){
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            throw new BadRequestException(errorMessage);
        }
        if(existing != null){
            throw new AlreadyExistException("중복된 E-Mail 입니다.");
        }

        memberServiceImpl.save(memberRequestDto);
        return "redirect:/register?success";
    }
}
