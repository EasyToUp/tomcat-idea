package cn.it.study.web.servlet;

import cn.it.study.domain.User;
import cn.it.study.service.UserService;
import cn.it.study.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter( "id" );
        UserService service = new UserServiceImpl();
        User user = service.findUserById( id );
        req.setAttribute( "user", user );
        req.getRequestDispatcher( "/update.jsp" ).forward( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
