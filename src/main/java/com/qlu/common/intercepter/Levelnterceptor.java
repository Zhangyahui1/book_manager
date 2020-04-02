package com.qlu.common.intercepter;

import com.qlu.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Levelnterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Integer level = user.getLevel();
        if (level>0){
            //true是管理员
            return true;
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
        return false;
    }
}
