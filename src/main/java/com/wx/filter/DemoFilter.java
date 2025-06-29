package com.wx.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override//初始化方法，该方法只会在启动时调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法执行了");

    }

    @Override//拦截到请求后调用，会执行多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override//销毁方法，只执行一次
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
    }
}
