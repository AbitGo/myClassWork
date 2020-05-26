package com.action.exp5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/exp5Update")
public class updatePwd extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        String password = request.getParameter("newPassword");
        try {
            int result = new UserChecker().updateAccount(username,password);
            //0 修改失败
            //1 修改成功
            if(0==result){
                response.getWriter().print("<h1><font color='red'> " + username + "修改失败！</font></h1>");
            }else if(1==result){
                response.getWriter().print("<h1><font color='red'> " + username + "密码修改成功！</font></h1>");
            }
        } catch (SQLException e) {
            response.getWriter().print("<h1><font color='red'> " + username + "内部处理器错误！</font></h1>");
        }
    }
}
