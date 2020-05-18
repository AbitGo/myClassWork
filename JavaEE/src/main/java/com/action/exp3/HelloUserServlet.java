package com.action.exp3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloUserServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>Hello, " + userName + "</h3>");
        pw.println("</body>");
        pw.println("</html>");
    }
}

