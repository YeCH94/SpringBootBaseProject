package com.chj.bootbase.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    private String loginIdName;
    private String loginPasswordName;
    private String loginRedirectName;
    private String exceptionMsgName;
    private String defaultFailureUrl;

    public AuthFailureHandler(){
        this.loginIdName = "email";
        this.loginPasswordName = "password";
        this.loginRedirectName = "loginRedirect";
        this.exceptionMsgName = "securityException";
        this.defaultFailureUrl = "/login";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String loginId = request.getParameter(loginIdName);
        String loginPassword = request.getParameter(loginPasswordName);
        String loginRedirect = request.getParameter(loginRedirectName);

        request.setAttribute(loginIdName, loginId);
        request.setAttribute(loginPasswordName, loginPassword);
        request.setAttribute(loginRedirectName, loginRedirect);

        request.setAttribute(exceptionMsgName, exception.getMessage());
        request.getRequestDispatcher(defaultFailureUrl).forward(request,response);
    }
}
