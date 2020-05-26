package cn.it.study.web.servlet;

import cn.it.study.service.UserService;
import cn.it.study.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues( "uid" );
        UserService service = new UserServiceImpl();
        service.delSelecedUser( ids );
        resp.sendRedirect( req.getContextPath() + "/findUserByPageServlet" );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
