package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResponseServlet")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("mouse","two fly birds");
        resp.setHeader("mouse", "bjsxt");
        resp.addHeader("key", "thinkpad");
        resp.addHeader("key", "holle");

        resp.setContentType("text/html;charset=utf-8");
//        resp.setContentType("text/xml;charset=utf-8");
//        resp.setContentType("text/plain;charset=utf-8");
//        resp.getWriter().write("<b>fdfafada赵晋英</b>");
        resp.sendError(403, "this methed is not supported");
    }
}
