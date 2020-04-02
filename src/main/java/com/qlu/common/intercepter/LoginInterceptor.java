package com.qlu.common.intercepter;

import com.qlu.bean.User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 通过request对象获取session
        HttpSession session = request.getSession();
        // 判断session中是否有登录用户
        User user = (User) session.getAttribute("user");
        // 如果用户为空，则跳转到登录页面
        if (ObjectUtils.isEmpty(user)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login");
            return false;
        }
        return true;
    }
}

