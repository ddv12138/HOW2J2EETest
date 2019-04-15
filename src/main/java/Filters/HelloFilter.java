package Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String ip = servletRequest.getRemoteAddr();
        String url = req.getRequestURL().toString();
        System.out.printf("%s %s 访问了 %s%n", date, ip, url);
        filterChain.doFilter(req, servletResponse);
    }
}
