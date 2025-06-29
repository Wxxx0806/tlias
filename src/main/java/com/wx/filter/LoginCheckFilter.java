package com.wx.filter;

import com.alibaba.fastjson.JSONObject;
import com.wx.Pojo.Result;
import com.wx.utils.jwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    public static Logger log = LoggerFactory.getLogger(LoginCheckFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //1.获取请求的url
        String url = req.getRequestURI().toString();
        log.info("请求到的url：{}", url);


        //2.判断请求的url是否包含登录操作，如果包含，放行
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            filterChain.doFilter(req, resp);
            return;
        }
        //3.获取请求头的token
        String jwt = req.getHeader("token");


        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象JSON--fastJSON
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            jwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象JSON--fastJSON
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }

        //6.放行
        log.info("此处进行放行操作");
        filterChain.doFilter(req, resp);
    }
}
