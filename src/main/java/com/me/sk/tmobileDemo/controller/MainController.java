package com.me.sk.tmobileDemo.controller;

import com.me.sk.tmobileDemo.model.GenericError;
import com.me.sk.tmobileDemo.model.NumValue;
import com.me.sk.tmobileDemo.model.error.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController @Slf4j
public class MainController {


    @GetMapping(value = {"/", "/index.html"})
    public ModelAndView mainPage(ModelAndView model) throws Exception {
        model.setViewName("home");
        return model;
    }

    @GetMapping("/add-ten/v1/{numRaw}")
    public NumValue doAddTenOp(@PathVariable String numRaw){
        try{
            int num = Integer.parseInt(numRaw);
            return new NumValue(num+10);
        } catch (Exception e){
            throw new GenericException(10, "Missing, Null or Bad num value in the request!");
        }
    }

    @GetMapping("/adder/v1/{num1Raw}/{num2Raw}")
    public NumValue doAddOp(@PathVariable String num1Raw, @PathVariable String num2Raw){
        try{
            int num1 = Integer.parseInt(num1Raw);
            int num2 = Integer.parseInt(num2Raw);
            return new NumValue(num1+num2);
        } catch (Exception e){
            throw new GenericException(11, "Missing, Null or Bad num value in the request, either num1 or num2!");
        }
    }


    @RestControllerAdvice
    static class MainControllerAdvice{

        @ExceptionHandler(Exception.class)
        public GenericError handleUnhandledException(Exception e, HttpServletResponse response){
            log.error("Unhandled Error: ", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new GenericError(500, "Server Error!! " + e.getMessage());
        }

        @ExceptionHandler(GenericException.class)
        public GenericError handleGenericException(GenericException e, HttpServletResponse response){
            log.error("Generic error: {}", e, e);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new GenericError(e.getCode(), e.getMsg());
        }
    }
}


