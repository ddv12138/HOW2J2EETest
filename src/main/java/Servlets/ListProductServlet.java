package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.DAO.ProductMapper;
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
        ProductMapper pm = session.getMapper(ProductMapper.class);
        ProductDao pd = session.getMapper(ProductDao.class);

        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map<String, Object> par = new HashMap<>();
        if (null == page || null == limit) {
            return;
        }
        par.put("offset", (long) ((Integer.parseInt(page) - 1) * Integer.parseInt(limit)));
        par.put("limit", Integer.parseInt(limit));
        List<Product> products = pm.ListProduct(par);
        for (Product p : products) {
            if (null != p.getCategory()) {
                p.setCategoryName(p.getCategory().getName());
            }
        }
        long count = pd.countByExample(new ProductExample());
        Map<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("count", count);
        res.put("data", products);
        resp.getWriter().write(JSON.toJSONString(res));
    }
}
