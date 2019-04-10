package Servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class FIleUploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "";
        try {
            DiskFileItemFactory dfi = new DiskFileItemFactory();
            dfi.setSizeThreshold(1024 * 1024 * 50);
            ServletFileUpload sfu = new ServletFileUpload(dfi);
            List<FileItem> files = sfu.parseRequest(req);
            for (FileItem item : files) {
                if (!item.isFormField()) {
                    System.out.println(item.getName());
                    filename = UUID.randomUUID().toString().replaceAll("-", "");
                    filename += "." + item.getName().split("\\.")[1];
                    String path = req.getServletContext().getRealPath("uploadFile");
                    File f = new File(path, filename);
                    f.getParentFile().mkdirs();
                    try (InputStream is = item.getInputStream();
                         OutputStream os = new FileOutputStream(f)) {
                        byte[] cache = new byte[1000];
                        while (is.read(cache) > 0) {
                            os.write(cache);
                        }
                        os.flush();
                    }
                }
            }
            String html = "<img width='200' height='150' src='uploadFile/%s' />";
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.format(html, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
