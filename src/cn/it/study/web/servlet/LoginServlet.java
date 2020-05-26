package cn.it.study.web.servlet;

import cn.it.study.domain.User;
import cn.it.study.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding( "utf-8" );
        //获取数据
        String verifycode = req.getParameter( "verifycode" );//验证码
        //校验验证码
        HttpSession session = req.getSession();
        String checkcode_service = (String) session.getAttribute( "CHECKCODE_SERVICE" );
        session.removeAttribute( "CHECKCODE_SERVICE" );//去除验证码 确保验证码一次性
        if (!checkcode_service.equalsIgnoreCase( verifycode )) {
            req.setAttribute( "login_msg", "验证码错误！" );
            req.getRequestDispatcher( "/login.jsp" ).forward( req, resp );
            return;
        }
        //封装对象
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate( user, parameterMap );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用 service 查询
        UserServiceImpl service = new UserServiceImpl();
        User loginUer = service.login( user );
        if (loginUer != null) {
            session.setAttribute( "user", loginUer );
            resp.sendRedirect( req.getContextPath() + "/index.jsp" );
        } else {
            req.setAttribute( "login_msg", "用户名或密码错误!" );
            req.getRequestDispatcher( "/login.jsp" ).forward( req, resp );
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
