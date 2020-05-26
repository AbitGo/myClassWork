package com.action.exp4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//使用annotion进行接口注册
@WebServlet("/ServletCookies")
public class ServletCookie extends HttpServlet {
    public ServletCookie() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //定义一个名为username，值为Tom的cookie
        Cookie cookie = new Cookie(username, password);
        //指定客户端返回的cookie路径
        cookie.setPath("");
        cookie.setComment("This is an Cookie");
        response.addCookie(cookie);
        request.getRequestDispatcher("exp4_index.jsp").forward(request, response);

    }
}
