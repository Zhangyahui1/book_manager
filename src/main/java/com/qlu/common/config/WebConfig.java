package com.qlu.common.config;

import com.qlu.common.intercepter.Levelnterceptor;
import com.qlu.common.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:d:/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> arr = new ArrayList<>();
        arr.add("/_MACOSX**");
        arr.add("/css/**");
        arr.add("/fonts/**");
        arr.add("/image/**");
        arr.add("/img/**");
        arr.add("/js/**");
        arr.add("/plugins/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(arr)
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/testName");

        registry.addInterceptor(new Levelnterceptor()).addPathPatterns("/admin/**");
    }
}
