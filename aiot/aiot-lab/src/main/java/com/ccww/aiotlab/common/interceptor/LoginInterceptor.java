package com.ccww.aiotlab.common.interceptor;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.common.utils.JwtTokenUtil;
import com.ccww.aiotlab.common.utils.UserThreadLocalUtil;
import com.ccww.aiotlab.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法，直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //如果是方法探测，直接通过
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("token");
//        System.out.println(jwtTokenUtil.isTokenExpired(token));
        User loginUser = jwtTokenUtil.getUserFromToken(token);

        if (loginUser==null){
            RespResult result = RespResultUtil.failure("无权限,请登陆后查看", 403);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
            return false;
        }
        //将登陆用户放入线程对象
        UserThreadLocalUtil.put(loginUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalUtil.remove();
    }
}
