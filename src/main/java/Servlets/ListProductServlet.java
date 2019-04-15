package Servlets;

import CRUD.DAO.ProductDao;
import CRUD.JavaBean.Product;
import CRUD.JavaBean.ProductExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ListProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String resource = "mybatis-config.xml";
        InputStream reis = Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reis);
        SqlSession session = ssf.openSession();
        ProductDao pm = session.getMapper(ProductDao.class);
        ProductExample pe = new ProductExample();
        pe.setOrderByClause("id,name,price,cid");
        List<Product> products = pm.selectByExample(pe);
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>id</td><td>name</td><td>price</td><td>cid</td></tr>\r\n");
        String trFormat = "<tr><td>%s</td><td>%s</td><td>%f</td><td>%s</td><td><a href='editProduct?id=%s'>modify</a></td><td><a href='deleteProduct?id=%s'>delete</a></td></tr>\r\n";
        for (Product product : products) {
            String tr = String.format(trFormat, product.getId(), product.getName(), product.getPrice(), product.getCid(), product.getId(), product.getId());
            sb.append(tr);
        }
        sb.append("</table>");
        resp.getWriter().write(sb.toString());
    }
}
