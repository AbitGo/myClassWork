package com.action.exp5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/exp5Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //可以通过过滤器的方式进行设置,设置语言编码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        //获得前台输入的账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            int result = new UserChecker().addAccount(username,password);
            //0 账号不存在 1 登陆成功 2 密码错误
            if(0==result){
                response.getWriter().print("<h1><font color='red'> " + username + "注册失败，账号已经存在！</font></h1>");
            }else if(1==result){
                response.sendRedirect("exp5_login.jsp");
            }
        } catch (SQLException e) {
            response.getWriter().print("<h1><font color='red'> " + username + "内部处理器错误！</font></h1>");
        }
    }
}
