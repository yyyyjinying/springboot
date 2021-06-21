package com.bjsxt.controller;

import com.bjsxt.pojo.Rusers;
import com.bjsxt.service.RusersService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class RusersController {
    @Resource
    RusersService rusersServiceImpl;

    private Logger logger = LoggerFactory.getLogger(RusersController.class);

    @RequestMapping("register")
    public String register(Rusers rusers, MultipartFile file, HttpServletRequest req){

        String filename = UUID.randomUUID().toString()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String path = req.getServletContext().getRealPath("images")+"/"+ filename;

//        Logger logger = Logger.getLogger(String.valueOf(RusersController.class));

        logger.info("下载路径：zhaojinying"+path);



        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rusers.setPhoto(filename);

        int index = rusersServiceImpl.insRegister(rusers);
        if(index > 0){
            req.getSession().setAttribute("rusers",rusers);
            return "redirect:/showList";

        } else {
            return "redirect:/registor.jsp";
        }
    }


}
