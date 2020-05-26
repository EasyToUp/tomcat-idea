package cn.it.study.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        resp.setHeader( "pragma", "no-cache" );
        resp.setHeader( "cache-control", "no-cache" );
        resp.setHeader( "expires", "0" );

        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色
        g.setColor( Color.GRAY );
        //填充图片
        g.fillRect( 0, 0, width, height );

        //产生的哥随机验证码，
        String checkCode = getCheckCode();
        //将验证码放入HTTPSession中
        req.getSession().setAttribute( "CHECKCODE_SERVICE", checkCode );

        //设置画笔颜色为黄色
        g.setColor( Color.YELLOW );
        //设置字体大小
        g.setFont( new Font( "黑体", Font.BOLD, 24 ) );
        //向图片写入验证码
        g.drawString( checkCode, 15, 25 );
        //将内存中的图片输出的浏览器
        ImageIO.write( image, "PNG", resp.getOutputStream() );
    }

    public String getCheckCode() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt( 62 );
            stringBuffer.append( str.charAt( number ) );
        }
        return stringBuffer.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet( req, resp );
    }
}
