package com.chj.bootbase.controller;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.error.BadRequestException;
import com.chj.bootbase.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class UserFindController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/findMember")
    public String findPage() {
        return "findMember";
    }

    @PostMapping("/findMember")
    public ModelAndView findMember(@RequestParam(value = "email") String email) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode(email,"UTF-8");
        Member result = memberService.findPassword(decode);
        ModelAndView mav = new ModelAndView("findMember");
        System.out.println(decode);

        if(result == null){
            throw new BadRequestException("이메일이 유효하지 않습니다.");
        }
        mav.addObject("member", result);
        return mav;
    }
}
