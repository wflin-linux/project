package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserList")
    public RespResult getUserList(Integer page,Integer limit){
        return userService.getUserList(page,limit);
    }

    @DeleteMapping("/deleteUser/{userid}")
    public RespResult deleteUser(@PathVariable Integer userid){
        return userService.deleteUser(userid);
    }

    @PutMapping("/updateUser")
    public RespResult updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
