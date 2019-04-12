package Servlets;

import CRUD.DAO.ProductMapper;
import CRUD.JavaBean.Product;
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

public class addProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product p = new Product(name, Double.parseDouble(price));
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
            ProductMapper pm = session.getMapper(ProductMapper.class);
            pm.Insert(p);
            session.commit();
            resp.getWriter().println("success");
            resp.sendRedirect("listProduct");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println(e.getMessage());
            session.rollback();
        } finally {
            session.close();
        }
    }
}
