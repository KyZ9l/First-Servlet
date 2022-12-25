package com.dmdev.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sum")
public class SumServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Я создал сервлет!!))");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession();
        Integer sum = (Integer) session.getAttribute("sum");
        if(sum==null)
            sum=0;

        var n = req.getParameter("n");
        sum+=Integer.parseInt(n);

        session.setAttribute("sum",sum);
        session.setMaxInactiveInterval(-1);

        try (var out = resp.getWriter()) {

            out.println("<html>");
            out.println("<head> <title> CalculatorServlet </title> </head>");
            out.println("<body>");
            out.println("<h1> Sum == " + sum + "</h1>");
            out.println("</body>");
            out.println("</html>");

        }

    }

    @Override
    public void destroy() {
        System.out.println("я удалилил сервлет");
        super.destroy();

    }
}
