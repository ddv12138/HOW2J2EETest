package Servlets;

import CRUD.DAO.CategoryDao;
import CRUD.JavaBean.Category;
import CRUD.JavaBean.CategoryExample;
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
        resp.getWriter().write(JSON.toJSONString(categories));
    }
}
