package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
        try {
            request.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            System.out.println("获取单值参数name:" + request.getParameter("name"));
            String[] hobits = request.getParameterValues("hobits");
            System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));

            System.out.println("通过 getParameterMap 遍历所有的参数： ");
            Map<String, String[]> parameters = request.getParameterMap();
            for (String key : parameters.keySet()) {
                for (String value : parameters.get(key)) {
                    System.out.println(key + "---" + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
