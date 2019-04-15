package Servlets;

import CRUD.DAO.ProductDao;
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

public class EditProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String id = req.getParameter("id");
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        Product p = session.getMapper(ProductDao.class).selectByPrimaryKey(id);
        StringBuffer format = new StringBuffer();

        format.append("<!DOCTYPE html>");
        format.append("<form action='updateHero' method='post'>");
        format.append("名字 ： <input type='text' name='name' value='%s' > <br>");
        format.append("价格 ： <input type='text' name='hp'  value='%f' > <br>");
        format.append("cid： <input type='text' name='damage'  value='%s' > <br>");
        format.append("<input type='hidden' name='id' value='%s'>");
        format.append("<input type='submit' value='更新'>");
        format.append("</form>");

        String html = String.format(format.toString(), p.getName(), p.getPrice(), p.getCid(), p.getId());
        resp.getWriter().write(html);
    }
}
