package com.ccww.aiotlab.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public RespResult getUserList(Integer page, Integer limit) {
        Page<User> mybatisPage = new Page<>(page,limit);
        //等于0未同意
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(User::getWhoAgree,0);
        Page<User> userPage = userMapper.selectPage(mybatisPage, wrapper);

        System.out.println(userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
        return RespResultUtil.success(userList).setCode(0).setMsg("").setCount(userPage.getTotal());
    }

    public RespResult deleteUser(Integer userid) {
        userMapper.deleteById(userid);
        return RespResultUtil.success("删除成功");
    }

    public RespResult updateUser(User user) {

        userMapper.updateById(user);
        return RespResultUtil.success();
    }
}




