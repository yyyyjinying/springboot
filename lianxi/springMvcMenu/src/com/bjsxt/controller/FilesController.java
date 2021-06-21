package com.bjsxt.controller;

import com.bjsxt.pojo.Files;
import com.bjsxt.service.FilesService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class FilesController {

    @Resource
    private FilesService filesServiceImpl;

    @RequestMapping("showList")
    public String show(Model model){
        List<Files> list = filesServiceImpl.show();

        Logger logger = LoggerFactory.getLogger(FilesController.class);
        logger.info("nini"+String.valueOf(list));


        model.addAttribute("list", list);
        return "/main.jsp";
    }

    @RequestMapping("wdownload")
    public void download(int id,String name,HttpServletResponse res,HttpServletRequest req) throws IOException{
        filesServiceImpl.updCount(id,name);
        res.setHeader("Content-Disposition", "attachment;filename="+name);
        ServletOutputStream os = res.getOutputStream();
        File file = new File(req.getServletContext().getRealPath("images"),name);
        os.write(FileUtils.readFileToByteArray(file));
        os.flush();
        os.close();
    }




}
