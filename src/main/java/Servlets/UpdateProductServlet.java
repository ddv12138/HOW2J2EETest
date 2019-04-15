package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.JavaBean.Product;
import CRUD.JavaBean.ProductExample;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        p.setId(req.getParameter("id"));
        p.setName(req.getParameter("name"));
        p.setCid(req.getParameter("cid"));
        Float price = StringUtils.isNullOrEmpty(req.getParameter("price")) ? null : Float.parseFloat(req.getParameter("price"));
        p.setPrice(price);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        try (SqlSession session = ssf.openSession()) {
            ProductDao pd = session.getMapper(ProductDao.class);
            pd.updateByPrimaryKey(p);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("listProduct");
    }
}
