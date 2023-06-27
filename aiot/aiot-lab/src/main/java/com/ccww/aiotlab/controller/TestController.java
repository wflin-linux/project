package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.common.utils.UserThreadLocalUtil;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.entity.UserTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;


@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public RespResult showUser(Integer page, Integer limit) {
        // layui自动发送页码page，以及每页条数limit
        System.out.println(page);
        System.out.println(limit);
        ArrayList<UserTest> list = new ArrayList<>();
        list.add(UserTest.builder().id(1).username("123").sex("男").build());
        list.add(UserTest.builder().id(2).username("456").sex("女").build());
        /*
           经过测试响应给layui表格的数据一定要是这个样子
           其中数据放在list中
           setCount()中放数据库总数据，用于前端分页展示
           .setCode(0).setMsg("")这两个一定要保持这样，传入其他值会出错
            我翻了文档，这是规定好的默认格式，只有这样前端才能解析数据

         */
        return RespResultUtil.success(list).setCode(0).setMsg("").setCount(20l);
    }

    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    @ResponseBody
    public void createTicket(MultipartHttpServletRequest request) {
        log.info("createTicket begin.");
        try {
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
                MultipartFile file = multipartRequest.getFile("file");
                System.out.println("上传的文件名称:" + file.getOriginalFilename());
                System.out.println("上传的文件大小:" + file.getSize());
                // 接收其他表单参数
                String name = multipartRequest.getParameter("param");
                System.out.println("name:" + name);
            }


        } catch (Exception exc) {
            exc.printStackTrace();
            log.error("createTicket error: " + exc.getMessage());
        }
    }
}
