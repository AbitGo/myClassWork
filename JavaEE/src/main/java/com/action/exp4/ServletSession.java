package com.action.exp4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ServletSession")
public class ServletSession extends HttpServlet {
    public ServletSession() {
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        //new 一个session来保存user、dateTime对象
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", username);
        session.setAttribute("loginTime", dateTime);
        session.setAttribute("password",password);
        session.setAttribute("sessionId", session.getId());

        String imageCode = (String) request.getSession().getAttribute("randomString");
        String checkText = (String) request.getParameter("checkText");
        System.out.println("imageCode:"+imageCode+" checkText:"+checkText);
        if(imageCode.equalsIgnoreCase(checkText)){
            session.setAttribute("loginInfo", "登陆成功");
            //请求转发
            request.getRequestDispatcher("exp4_index2.jsp").forward(request, response);
        }else{
            session.setAttribute("loginInfo", "验证码不正确");
            //请求转发,并且返回数据
            response.sendRedirect("exp4_login2.jsp");
        }


    }

}
