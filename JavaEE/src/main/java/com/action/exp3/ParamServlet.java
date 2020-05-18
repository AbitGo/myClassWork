package com.action.exp3;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ParamServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        name = new String(name.getBytes("UTF-8"),"UTF-8");
        String age = request.getParameter("age");
        age = new String(age.getBytes("UTF-8"),"UTF-8");

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>姓名："+name+"</h3><p>");
        pw.println("<h3>年龄："+age+"</h3><p>");
        pw.println("</body>");
        pw.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
}
