package Filters;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginStateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String pattern = ".*\\.(js|jpg|png|css)";
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String s = req.getRequestURI().toLowerCase();
        if (req.getRequestURI().toLowerCase().endsWith("index.html")
                || req.getRequestURI().toLowerCase().endsWith("index")
                || req.getRequestURI().toLowerCase().endsWith("register.html")
                || req.getRequestURI().toLowerCase().endsWith("register")
                || req.getRequestURI().toLowerCase().endsWith("login")
                || req.getRequestURI().toLowerCase().endsWith("login.html")
                || req.getRequestURI().toLowerCase().matches(pattern)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String username = (String) req.getSession().getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            resp.sendRedirect("index.html");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
