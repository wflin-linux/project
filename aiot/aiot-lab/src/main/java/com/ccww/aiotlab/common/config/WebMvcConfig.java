package com.ccww.aiotlab.common.config;

import com.ccww.aiotlab.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${upload.imgPath}")
    private String imgPath;

    @Value("${upload.filePath}")
    private String filePath;

    @Value("${upload.pathPatterns}")
    private String pathPatterns;

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 排除
        List<String> patterns = new ArrayList<String>();
        patterns.add("/register");
        patterns.add("/login/back");
        patterns.add("/login/fonts");
        patterns.add("/register/getcode/**");
        patterns.add("/competition/upload/img");
        patterns.add("/competition/upload/editorUpload");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + pathPatterns + "/**").addResourceLocations("file:" + imgPath, "file:" + filePath);
    }
}
