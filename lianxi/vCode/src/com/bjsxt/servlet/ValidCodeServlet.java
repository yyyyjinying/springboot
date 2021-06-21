package com.bjsxt.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet("/validcode")
public class ValidCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // 创建一张图片
        // 单位：像素

        BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);

        // 设置画笔
        Graphics2D gra = image.createGraphics();

        gra.setColor(Color.WHITE);
        gra.fillRect(0,0,200,100);

        ArrayList<Integer> randList = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i< 4;i++){
            randList.add(random.nextInt(10));
        }
        // 设置字体
        gra.setFont(new Font("宋体",Font.ITALIC|Font.BOLD,40));
        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, Color.GRAY};

        for(int i=0;i<randList.size();i++){
            gra.setColor(colors[random.nextInt(colors.length)]);
            gra.drawString(randList.get(i)+"",i*40,70+(random.nextInt(21)-10));

        }

        for(int i=0;i<2;i++){
            gra.setColor(colors[random.nextInt(colors.length)]);
            // 画线
            gra.drawLine(0,random.nextInt(101),200,random.nextInt(101));

        }

        ServletOutputStream outputStream = resp.getOutputStream();

        ImageIO.write(image,"jpg",outputStream);

        HttpSession session = req.getSession();
        String str = ""+randList.get(0)+randList.get(1)+randList.get(2)+randList.get(3);

        session.setAttribute("code", str);
        System.out.println("code: "+str);
    }
}
