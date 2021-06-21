package com.bjsxt.servlet;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.service.PeopleService;
import com.bjsxt.service.impl.PeopleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageSizeStr = req.getParameter("pageSize");
        int pageSize=2;
        if(pageSizeStr!=null&&!pageSizeStr.equals("")){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        String pageNumberStr = req.getParameter("pageNumber");
        int pageNumber = 1;
        if (pageNumberStr!=null&&!pageNumberStr.equals("")) {
            pageNumber = Integer.parseInt(pageNumberStr);
        }

        PeopleService pService = new PeopleServiceImpl();
        PageInfo pageInfo = pService.showPage(pageSize, pageNumber);

        req.setAttribute("PageInfo", pageInfo);

        System.out.println(pageInfo);

        req.getRequestDispatcher("/list.jsp").forward(req,resp);

    }
}
