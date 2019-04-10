package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            if ("admin".equals(name) && "123".equals(password)) {
                request.getRequestDispatcher("loginSuccess.html").forward(request, response);
            } else {
                response.sendRedirect("LoginFail.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
