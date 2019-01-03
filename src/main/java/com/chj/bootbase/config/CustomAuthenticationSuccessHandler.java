package com.chj.bootbase.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        boolean isAdmin = false;

        logger.info("AT onAuthenticationSuccess(...) function");

        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                isAdmin = true;
            }
        }
        if(isAdmin){
            response.sendRedirect("/admin");
        }else{
            response.sendRedirect("/user");
        }
    }
}
