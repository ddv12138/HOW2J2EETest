package Servlets;

import FileUploadUtil.FileUploadSteteCollection;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileUploadStatusServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String sessionid = req.getSession().getId();
        String filename = req.getParameter("filename");
        res.getWriter().println(JSON.toJSONString(FileUploadSteteCollection.get(sessionid, filename)));
    }
}
