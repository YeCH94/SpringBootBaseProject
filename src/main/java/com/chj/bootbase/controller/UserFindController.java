package com.chj.bootbase.controller;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.dto.FindRequestDto;
import com.chj.bootbase.error.BadRequestException;
import com.chj.bootbase.error.NotFoundException;
import com.chj.bootbase.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class UserFindController {

    @Autowired
    MemberServiceImpl memberService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("Find")
    public FindRequestDto findRequestDto() { return new FindRequestDto(); }

    @GetMapping("/findMember")
    public String findPage() {
        return "findMember";
    }

    @PostMapping("/findMember")
    public ModelAndView findMember(@RequestParam(value = "email") String email) throws UnsupportedEncodingException {
        String decoded = URLDecoder.decode(email,"UTF-8");
        Member member = memberService.findPassword(decoded);
        ModelAndView mav = new ModelAndView("updatePassword");

        if(member == null){
            throw new BadRequestException("이메일이 유효하지 않습니다.");
        }
        mav.addObject("email", member.getEmail());
        return mav;
    }

    @PostMapping(value = "/updatePassword", params = "email")
    public ModelAndView updatePassword(@RequestParam(value = "email") String email) {
        ModelAndView mav = new ModelAndView("updatePassword");
        mav.addObject("email", email);

        return mav;
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute("Find") @Valid FindRequestDto findRequestDto, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new BadRequestException(errorMessage);
        }

        System.out.println("바인딩 에러는 아님");

        String encoded = passwordEncoder.encode(findRequestDto.getPassword());

        int result= memberService.updatePassword(findRequestDto.getEmail(), encoded);

        System.out.println(result);

        if(result == 0) {
            throw new NotFoundException("존재하지 않는 유저입니다.");
        }

        return "index";
    }
}
