package Servlets;

import CRUD.DAO.CategoryDao;
import CRUD.DAO.ProductMapper;
import CRUD.JavaBean.Category;
import CRUD.JavaBean.CategoryExample;
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
import java.util.List;

public class ListProductServlet extends HttpServlet {
    SqlSession session = null;

    @Override
    public void init() throws ServletException {
        try {
            String resource = "mybatis-config.xml";
            InputStream reis = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reis);
            this.session = ssf.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        if (null != this.session) {
            ProductMapper pm = this.session.getMapper(ProductMapper.class);
            List<Product> products = pm.ListProduct();
            CategoryExample ce = new CategoryExample();
            ce.setOrderByClause("id");
            CategoryDao cm = session.getMapper(CategoryDao.class);
            List<Category> categories = cm.selectByExample(ce);
            StringBuffer sb = new StringBuffer();
            sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
            sb.append("<tr><td>id</td><td>name</td></tr>\r\n");
            String trFormat = "<tr><td>%s</td><td>%s</td></tr>\r\n";
            for (Category category : categories) {
                String tr = String.format(trFormat, category.getId(), category.getName());
                sb.append(tr);
            }
            sb.append("</table>");
            resp.getWriter().write(sb.toString());
        }
    }

    @Override
    public void destroy() {
        this.session.commit();
        this.session.close();
    }
}
