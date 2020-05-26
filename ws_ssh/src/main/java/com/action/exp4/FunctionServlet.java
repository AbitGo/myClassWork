package com.action.exp4;

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

@WebServlet("/servlet/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    static final char[] CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    //得到一个4位的随机字符数组
    public static Random random = new Random();

    static String getRandomString() {
        StringBuffer randomString = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            randomString.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return randomString.toString();
    }

    //获得随机颜色
    static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");  //设置类型
        String randomString = this.getRandomString();   //得到一个4位的验证码
        request.getSession().setAttribute("randomString", randomString);  //将验证字符串保存到会话中
        int width = 80, height = 25;  //设置验证码的宽高
        Color c = this.getRandomColor();   //得到一种随机颜色
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.fillRect(0, 0, width, height);  //此行缺少，图片背景变为黑色
        g.setColor(c);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString(randomString, 20, 20);
        //设置图片噪点
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            bi.setRGB(x, y, (int) (Math.random() * 255));
        }
        //设置图片干扰线
        for (int i = 0; i < 6; i++) {
            //随机获取干扰线的起点和终点
            int xstart = (int) (Math.random() * 80);
            int ystart = (int) (Math.random() * 25);
            int xend = (int) (Math.random() * 80);
            int yend = (int) (Math.random() * 25);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(xstart, ystart, xend, yend);
        }
        //输出验证码图片
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }
}
