package cn.it.study.web.servlet;

import cn.it.study.domain.PageBean;
import cn.it.study.domain.User;
import cn.it.study.service.UserService;
import cn.it.study.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding( "utf-8" );
        String currentPage = req.getParameter( "currentPage" );
        String rows = req.getParameter( "rows" );
        if (currentPage == null || "".equals( currentPage )) {
            currentPage = "1";
        }
        if (rows == null || "".equals( rows )) {
            rows = "5";
        }
        //获取条件查询的参数
        Map<String, String[]> condition = req.getParameterMap();
        UserService service = new UserServiceImpl();
        PageBean<User> pageBean = service.findUserByPage( currentPage, rows, condition );
        req.setAttribute( "pb", pageBean );
        req.setAttribute( "condition", condition );
        req.getRequestDispatcher( "/list.jsp" ).forward( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
