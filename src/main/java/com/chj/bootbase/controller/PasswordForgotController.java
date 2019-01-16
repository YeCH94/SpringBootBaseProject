package com.chj.bootbase.controller;

import com.chj.bootbase.domain.Mail;
import com.chj.bootbase.domain.Member;
import com.chj.bootbase.domain.PasswordResetToken;
import com.chj.bootbase.dto.PasswordForgotDto;

import com.chj.bootbase.repository.PasswordResetTokenRepository;
import com.chj.bootbase.service.EmailService;
import com.chj.bootbase.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotDto(){
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        Member member = memberService.findByEmail(form.getEmail());
        if (member == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setMember(member);
        token.setExpireDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(member.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", member);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }

}
