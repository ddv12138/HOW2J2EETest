package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.JavaBean.Product;
import CRUD.JavaBean.ProductExample;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream reis = Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reis);
        SqlSession session = ssf.openSession();
        ProductDao pm = session.getMapper(ProductDao.class);
        ProductExample pe = new ProductExample();
        pe.setOrderByClause("id,name,price,cid");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        pe.setOffset((long) ((Integer.parseInt(page) - 1) * Integer.parseInt(limit)));
        pe.setLimit(Integer.parseInt(limit));
        List<Product> products = pm.selectByExample(pe);
        long count = pm.countByExample(new ProductExample());
        pe = new ProductExample();
        Map<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("count", count);
        res.put("data", products);
        resp.getWriter().write(JSON.toJSONString(res));
    }
}
