package com.youga.silver.Interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    private static final String TIME_OUT = "t000001";

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {



        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if ("/login".equals(url)||"/submitLogin".equals(url)) {
            return true;
        }else {
            String username =  (String)request.getSession().getAttribute("loginUserSid");
            if(username == null){

                request.getRequestDispatcher("/login").forward(request, response);
               // response.getWriter().print(TIME_OUT);
                return false;
            }else
                return true;
        }
    }

}
