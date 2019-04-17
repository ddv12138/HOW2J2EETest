package Servlets;

import CRUD.DAO.UsertableDao;
import CRUD.JavaBean.Usertable;
import CRUD.JavaBean.UsertableExample;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp) {
        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sf.openSession();
            Map<String, Object> res = new HashMap<>();
            try {
                UsertableDao ud = session.getMapper(UsertableDao.class);
                UsertableExample example = new UsertableExample();
                example.createCriteria().andNameEqualTo(name);
                List<Usertable> users = ud.selectByExample(example);
                if (null == users || users.size() == 0) {
                    res.put("flag", false);
                    res.put("message", "用户不存在");
                    resp.getWriter().println(JSON.toJSONString(res));
                    return;
                }
                boolean flag = false;
                for (Usertable u : users) {
                    if (u.getName().equals(name) && u.getPassword().equals(password)) {
                        flag = true;
                        break;
                    }
                }
                res.put("flag", flag);
                resp.getWriter().println(JSON.toJSONString(res));
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
