package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.JavaBean.Product;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String datastr = req.getParameter("data");
        Product p = JSON.parseObject(datastr, Product.class);
        p.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
            ProductDao pm = session.getMapper(ProductDao.class);
            pm.insert(p);
            session.commit();
            resp.getWriter().println("success");
            resp.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println(e.getMessage());
            session.rollback();
        } finally {
            session.close();
        }
    }
}
