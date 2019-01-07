package com.chj.bootbase.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Getter
@Setter
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private String targetUrlParameter;
    private String defaultUrl;
    private boolean useReferer;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AuthSuccessHandler(){
        targetUrlParameter = "";
        defaultUrl = "/";
        useReferer = false;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        clearAuthAttributes(request);

        int intRedirectStrategy = decideRedirectStrategy(request,response);
        switch (intRedirectStrategy){
            case 1:
                useTargetUrl(request,response);
                break;
            case 2:
                useSessionUrl(request,response);
                break;
            case 3:
                useReferUrl(request, response);
                break;
            case 0:
                useDefaultUrl(request,response);
        }

    }

    private void clearAuthAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session == null){
            return ;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){
            requestCache.removeRequest(request,response);
        }
        String targetUrl = request.getParameter(targetUrlParameter);
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        String targetUrl = savedRequest.getRedirectUrl();
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    private void useReferUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String targetUrl = request.getHeader("REFERER");
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        redirectStrategy.sendRedirect(request,response,defaultUrl);
    }

    private int decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response){
        int result = 0;
        SavedRequest savedRequest = requestCache.getRequest(request,response);

        if(!"".equals(targetUrlParameter)){
            String targetUrl = request.getParameter(targetUrlParameter);
            if(StringUtils.hasText(targetUrl)){
                result = 1;
            } else{
                if(savedRequest != null){
                    result = 2;
                } else{
                    String refererUrl = request.getHeader("REFERER");
                    if(useReferer && StringUtils.hasText(refererUrl)){
                        result = 3;
                    } else{
                        result = 0;
                    }
                }
            }
            return result;
        }
        String refererUtl = request.getHeader("REFERER");
        if(useReferer && StringUtils.hasText(refererUtl)){
            result = 3;
        } else {
            result = 0;
        }
        return result;
    }
}