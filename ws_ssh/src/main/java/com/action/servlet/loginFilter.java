package com.action.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/*
常用配置项 urlPatterns
配置要拦截的资源
以指定资源匹配。例如"/index.jsp"
以目录匹配。例如"/servlet/*"
以后缀名匹配，例如"*.jsp"
通配符，拦截所有web资源。"/*"
 */

//通配符，拦截所有web资源。
@WebFilter(urlPatterns = "/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        //将所有的servlet设置语言编码为utf-8
        System.out.println("servlet被拦截并开始进行语言编码设置");
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("servlet放行");
    }
    //销毁
    public void destroy() {
        System.out.println("过滤器已经被销毁销毁");
    }
}
