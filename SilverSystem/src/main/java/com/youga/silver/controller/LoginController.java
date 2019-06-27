package com.youga.silver.controller;

import com.youga.silver.obj.MerchantInfo;
import com.youga.silver.service.LoginService;
import com.youga.silver.service.impl.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {


    LoginService logingService = new LoginServiceImpl();
    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        return "login";

    }

    @RequestMapping("/submitLogin")
    public void submitLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String acc = req.getParameter("acc");
        String pass = req.getParameter("pass");

        if (!logingService.checkAccount(acc,pass)){
            resp.getWriter().print(1);
            return ;
        }else {

            MerchantInfo shopInfo = logingService.getMerchantInfo(acc);
            resp.getWriter().print(shopInfo.getShopidMD5());

            HttpSession session = req.getSession();
            //新增session判断用户是否超时登录
            String sid = session.getId();
            session.setAttribute("loginUserSid", sid);
            session.setMaxInactiveInterval(30*60);

            //新增写入shopid给前端
            String shopid = shopInfo.getShopidMD5();
            Cookie shopidCookie = new Cookie("shopidCookie",shopid);
            shopidCookie.setPath("/");
            resp.addCookie(shopidCookie);


        }
    }

}
