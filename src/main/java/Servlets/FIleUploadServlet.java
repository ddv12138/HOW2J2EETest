package Servlets;

import FileUploadUtil.FIleUploadStatus;
import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FIleUploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String filename = "";
        try {
            DiskFileItemFactory dfi = new DiskFileItemFactory();
            dfi.setSizeThreshold(1024 * 1024 * 100);
            ServletFileUpload sfu = new ServletFileUpload(dfi);
            List<FileItem> files = sfu.parseRequest(req);
            for (FileItem item : files) {
                if (!item.isFormField()) {
                    filename = UUID.randomUUID().toString().replaceAll("-", "");
                    filename += "." + item.getName().split("\\.")[1];
                    String path = req.getServletContext().getRealPath("uploadFile");
                    File f = new File(path, filename);
                    f.getParentFile().mkdirs();
                    try (InputStream is = item.getInputStream();
                         OutputStream os = new FileOutputStream(f)) {
                        FIleUploadStatus fus;
                        if (null == req.getSession().getAttribute("fus")) {
                            fus = new FIleUploadStatus((String) req.getSession().getAttribute("username"), req.getRequestedSessionId(), item.getName(), item.getSize());
                        } else {
                            fus = (FIleUploadStatus) req.getSession().getAttribute("fus");
                        }
                        byte[] cache = new byte[10 * 100];
                        long readSize;
                        long transed = 0;
                        while ((readSize = is.read(cache)) > 0) {
                            transed += readSize;
                            fus.setBytePerSecond(readSize);
                            fus.setTransfered(transed);
                            req.getSession().setAttribute("fus", fus);
                            os.write(cache);
                        }
                        os.flush();
                    }
                }
            }
            String url = "uploadFile/" + filename;
            Map<String, Object> res = new HashMap<>();
            res.put("code", 0);
            res.put("url", url);
            resp.getWriter().println(JSON.toJSONString(res));
            req.getSession().removeAttribute("fus");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
