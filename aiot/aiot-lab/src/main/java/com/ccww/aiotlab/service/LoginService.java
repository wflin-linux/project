package com.ccww.aiotlab.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.common.utils.JwtTokenUtil;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.mapper.UserMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class LoginService {

    //循环加密次数
    @Value("${login.md5Num}")
    private int md5Num;
    //固定盐
    @Value("${login.salt}")
    private String configSalt;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private Cache<String, Object> caffeineCache;

    @Resource
    private JavaMailSender javaMailSender;

    /*
    注册
     */
    public RespResult register(User user) {

        //取验证码
        String code = (String) caffeineCache.getIfPresent(user.getEmail());
        if (code == null || !code.equals(user.getCode()) )
            return RespResultUtil.failure("验证码输入错误",1000);

        Map<String, String> md5Result = md5Password(user.getPassword());
        user.setPassword(md5Result.get("password"));
        user.setSalt(md5Result.get("salt"));
        user.setRealName(user.getRealName());
        user.setWhoAgree(0);

        userMapper.insert(user);

        return RespResultUtil.success("注册成功");
    }
    /*
    登录
     */
    public RespResult<String> loginBack(User user) {
        //根据账号查用户，如果不存在返回失败
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, user.getEmail());
        User databaseUser = userMapper.selectOne(wrapper);
        if (databaseUser == null)
            return RespResultUtil.failure("用户未注册",1000);

        //对传入的密码再次加密,与数据库结果进行比对
        String passwordCheck = passwordCheck(user.getPassword(), databaseUser.getSalt());

        if (!databaseUser.getPassword().equals(passwordCheck))
            return RespResultUtil.failure("密码错误",2000);

//        System.out.println(databaseUser);

        //获得token并传给前端
        String token = jwtTokenUtil.generateToken(databaseUser);
        return RespResultUtil.success("登陆成功",token);
    }
    /*
    获得验证码
     */
    public RespResult getCode(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(wrapper);

        if (user!=null){
            return RespResultUtil.failure("该用户已被注册",1000);
        }

        String code = getRandomNumCode(4);
        //将验证码放入缓存，并将其发送到指定邮箱
        caffeineCache.put(email,code);

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setText("欢迎注册aiot实验室账号，您的验证码为"+code);
        message.setSubject("注册aiot-lab");
        message.setTo(email);
        javaMailSender.send(message);

        return RespResultUtil.success("验证码以发送");
    }

    /*
    展示注册用户
     */
    public RespResult getRegisterList(Integer page, Integer limit) {
        Page<User> mybatisPage = new Page<>(page,limit);
        //等于0未同意
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getWhoAgree,0);
        Page<User> userPage = userMapper.selectPage(mybatisPage, wrapper);

        System.out.println(userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
        return RespResultUtil.success(userList).setCode(0).setMsg("").setCount(userPage.getTotal());
    }

    /**
     * @param password 加密前的密码
     * @return 加密后的密码和用于加密的随机盐
     */
    private Map<String, String> md5Password(String password) {

        // 生成随机字符串，用于对用户密码加盐
        int randomNum = new Random().nextInt(20);
        String salt = UUID.randomUUID().toString().substring(randomNum, randomNum + 10);

        String md5Password = password;

        // 根据配置文件设定的加密次数进行加密，偶数此为在前加固定盐，奇数为在后加随机盐
        for (int i = 0; i < md5Num; i++) {
            if (i % 2 == 0) {
                md5Password = DigestUtils.md5DigestAsHex((configSalt + md5Password).getBytes(StandardCharsets.UTF_8));
            } else {
                md5Password = DigestUtils.md5DigestAsHex((md5Password + salt).getBytes(StandardCharsets.UTF_8));
            }
        }

        HashMap<String, String> res = new HashMap<>();
        res.put("password", md5Password);
        res.put("salt", salt);

        return res;
    }

    /**
     * @param password 前端传来的密码
     * @param salt     用户对应的随机盐
     * @return 密码验证的结果
     */
    private String passwordCheck(String password, String salt) {

        String receivePassword = password;

        // 加密传来的密码用于比对
        for (int i = 0; i < md5Num; i++) {
            if (i % 2 == 0) {
                receivePassword = DigestUtils.md5DigestAsHex((configSalt + receivePassword).getBytes(StandardCharsets.UTF_8));
            } else {
                receivePassword = DigestUtils.md5DigestAsHex((receivePassword + salt).getBytes(StandardCharsets.UTF_8));
            }
        }

        return receivePassword;
    }

    /**
     * 生成随机数字符串
     * @param number 长度
     * @return
     */
    private String getRandomNumCode(int number) {
        String codeNum = "";
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
            codeNum += numbers[next % 10];
        }

        return codeNum;
    }

    /*
    同意注册
     */
    public RespResult agreeRegister(Integer userid,String email) {
        User user = new User();
        user.setId(userid).setWhoAgree(1);
        userMapper.updateById(user);

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setText("恭喜你的aiot-lab账号:"+email+",现以审核通过，可以开始使用");
        message.setSubject("注册aiot-lab申请通过");
        message.setTo(email);
        javaMailSender.send(message);

        return RespResultUtil.success("已同意");
    }

    public RespResult<Map<String,String>> loginFonts(User user) {
        RespResult<String> stringRespResult = loginBack(user);
        HashMap<String, String> res = new HashMap<>();
        res.put("token",stringRespResult.getData());
        res.put("realName",userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail,user.getEmail())).getRealName());

        return RespResultUtil.success("登陆成功",res);
    }
}
