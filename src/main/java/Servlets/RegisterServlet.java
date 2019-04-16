package Servlets;

import com.alibaba.fastjson.JSON;

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
            System.out.println("通过 getParameterMap 遍历所有的参数： ");
            Map<String, String[]> parameters = request.getParameterMap();
            System.out.println(JSON.toJSONString(parameters));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
