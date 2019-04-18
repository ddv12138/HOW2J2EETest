package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.JavaBean.Product;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (!StringUtils.isNullOrEmpty(id)) {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = ssf.openSession();
            try {
                ProductDao pm = session.getMapper(ProductDao.class);
                Product p = new Product();
                p.setId(id);
                pm.deleteByPrimaryKey(id);
                session.commit();
                resp.getWriter().println("删除成功");
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
            } finally {
                session.close();
            }
        }
    }
}
