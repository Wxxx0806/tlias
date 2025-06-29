package com.wx.controller;


import com.wx.Pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class SessionController {

    public static Logger log= LoggerFactory.getLogger(SessionController.class);
    /*设置Cookie
    设置Cookie实际上就是服务器端要给浏览器响应cookie数据
    响应回来的是set-cookie*/

    @GetMapping("/c1")
    public Result Cookie1(HttpServletResponse response) {
        response.addCookie(new Cookie("login_name", "itheima"));//设置一个Cookie来响应一个Cookie
        return Result.success();
    }

    /*
     * 客户端向服务器发送Cookie*/

    @GetMapping("/c2")
    public Result Cookie2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_name")) {
                System.out.println("login_name:" + cookie.getValue());
            }
        }

        return Result.success();
    }


    /*
    向Httpsession存储值*/
    @GetMapping("/s1")
    public Result session1(HttpSession session) {

        log.info("Http-session1的值为{}", session.hashCode());
        session.setAttribute("loginUser", "Tom");//往session中存数据
        return Result.success();
    }

    /*
     * 从HttpSession中获取值*/
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("Http-session2的值为{}", session.hashCode());


        Object loginUser = session.getAttribute("loginUser");//从session中取数据
        log.info("LoginUser-{}", loginUser);

        return Result.success();
    }
}
