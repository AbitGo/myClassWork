package com.action.exp5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.out;

@WebServlet("/exp5Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //可以通过过滤器的方式进行设置,设置语言编码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        //获得前台输入的账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            int result = new UserChecker().checkUserInfo(username,password);
            //0 账号不存在
            //1 登陆成功
            //2 密码错误
            if(0==result){
                response.sendRedirect("exp5_register.jsp");
            }else if(1==result){
                response.sendRedirect("exp5_welcome.jsp");
                //将数据保留至session中
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
            }else{
                response.getWriter().print("<h1><font color='red'> " + username + "密码错误！</font></h1>");
            }
        } catch (SQLException e) {
            response.getWriter().print("<h1><font color='red'> " + username + "内部处理器错误！</font></h1>");
        }
    }
}
