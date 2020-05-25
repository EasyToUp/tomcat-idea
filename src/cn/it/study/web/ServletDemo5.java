package cn.it.study.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo5")
public class ServletDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        Cookie cookie = new Cookie( "msg", "hello" );
        resp.addCookie( cookie );
        System.out.println( method );
        PrintWriter writer = resp.getWriter();
        writer.write( method + "test method" );
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie1 : cookies) {
            String value = cookie1.getValue();
        }
        HttpSession session = req.getSession();
        String id = session.getId();
        writer.write( "sessionId:" + id );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
