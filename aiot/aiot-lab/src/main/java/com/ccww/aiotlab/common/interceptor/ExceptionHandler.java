package com.ccww.aiotlab.common.interceptor;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public RespResult vaildException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> error = new HashMap<>();
        bindingResult.getFieldErrors().forEach((field)->{
            error.put(field.getField(), field.getDefaultMessage());
        });
       return RespResultUtil.failure("参数有误",error);
    }
}
