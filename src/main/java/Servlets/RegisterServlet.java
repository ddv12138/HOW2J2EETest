package Servlets;

import CRUD.DAO.UsertableDao;
import CRUD.JavaBean.Usertable;
import CRUD.JavaBean.UsertableExample;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = ssf.openSession();
            try {
                UsertableDao ud = session.getMapper(UsertableDao.class);
                UsertableExample example = new UsertableExample();
                example.createCriteria().andNameEqualTo(name);
                List<Usertable> users = ud.selectByExample(example);
                if (null != users && users.size() > 0) {
                    Map<String, Object> res = new HashMap<>();
                    res.put("flag", false);
                    res.put("message", "用户已存在");
                    resp.getWriter().println(JSON.toJSONString(res, true));
                    return;
                }
                Usertable u = new Usertable();
                u.setName(name);
                u.setPassword(password);
                if (ud.insert(u) > 0) {
                    Map<String, Object> res = new HashMap<>();
                    res.put("flag", true);
                    res.put("message", "注册成功");
                    resp.getWriter().println(JSON.toJSONString(res, true));
                }
                session.commit();
            } catch (Exception e) {
                session.rollback();
                throw e;
            } finally {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
