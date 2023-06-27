package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.service.LoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/register")
    public RespResult register(@RequestBody User user){
        return loginService.register(user);
    }

    @PostMapping("/login/back")
    public RespResult<String> loginBack(@RequestBody User user){
//        System.out.println(user);
        return loginService.loginBack(user);
    }

    @PostMapping("/login/fonts")
    public RespResult<Map<String,String>> loginFonts(@Validated @RequestBody User user){
        return loginService.loginFonts(user);
    }

    @GetMapping("/register/getcode/{email}")
    public RespResult getCode(@PathVariable String email){
        System.out.println(email);
        return loginService.getCode(email);
    }

    @GetMapping("/getRegisterList")
    public RespResult getRegisterList(Integer page,Integer limit){
        return loginService.getRegisterList(page,limit);
    }

    @PutMapping("/agree/register/{userid}/{email}")
    public RespResult agreeRegister(@PathVariable("userid") Integer userid,
                                    @PathVariable("email") String email){
        System.out.println(userid);
        System.out.println(email);
        return loginService.agreeRegister(userid,email);
    }
}
