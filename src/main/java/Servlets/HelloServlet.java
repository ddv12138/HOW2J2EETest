package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("初始化hello");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<p>Hello Servlet!</p>");
            response.getWriter().println("<p>浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + request.getRequestURL() + "</p>");
            response.getWriter().println("<p>浏览器发出请求的资源名部分，去掉了协议和主机名: " + request.getRequestURI() + "</p>");
            response.getWriter().println("<p>请求行中的参数部分: " + request.getQueryString() + "</p>");
            response.getWriter().println("<p>浏览器所处于的客户机的IP地址: " + request.getRemoteAddr() + "</p>");
            response.getWriter().println("<p>浏览器所处于的客户机的主机名: " + request.getRemoteHost() + "</p>");
            response.getWriter().println("<p>浏览器所处于的客户机使用的网络端口: " + request.getRemotePort() + "</p>");
            response.getWriter().println("<p>服务器的IP地址: " + request.getLocalAddr() + "</p>");
            response.getWriter().println("<p>服务器的主机名: " + request.getLocalName() + "</p>");
            response.getWriter().println("<p>得到客户机请求方式: " + request.getMethod() + "</p>");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                String value = request.getHeader(header);
                response.getWriter().println("<p>" + header + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + value + "</p>");
            }
            response.getWriter().println(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
