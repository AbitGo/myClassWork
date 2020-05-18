package com.action.exp3;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RedirectServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException,ServletException{

        // 此处添加若干代码
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        if(name.equals("root") &&password.equals("root") ){
            //response.sendRedirect("exp3_welcome.jsp");
            RequestDispatcher rd=request.getRequestDispatcher("exp3_welcome.jsp");
            rd.forward(request, response);

        }else{
            response.sendRedirect("exp3_error.jsp");
        }
    }
}
