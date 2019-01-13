package com.chj.bootbase.config;

import com.chj.bootbase.domain.ErrorResponse;
import com.chj.bootbase.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    public final ModelAndView handleInternalServerException(Exception ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("error", error);
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ModelAndView handleAlreadyExistException(Exception ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("register");
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
        mav.addObject("error", error);
        return mav;
    }

    @ExceptionHandler(BadRequestException.class)
    public final ModelAndView handleBadRequestException(Exception ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("register");
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        mav.addObject("error", error);
        return mav;
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ModelAndView handleForbiddenException(Exception ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
        mav.addObject("error", error);
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ModelAndView handleNotFoundException(Exception ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        mav.addObject("error", error);
        mav.setViewName("error");
        return mav;
    }
}
