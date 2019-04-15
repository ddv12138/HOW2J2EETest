package Servlets;

import CRUD.DAO.CategoryDao;
import CRUD.JavaBean.Category;
import CRUD.JavaBean.CategoryExample;
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

public class ListCategoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resource = "mybatis-config.xml";
        InputStream reis = Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reis);
        SqlSession session = ssf.openSession();
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
