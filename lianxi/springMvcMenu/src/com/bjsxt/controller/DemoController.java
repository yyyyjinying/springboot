package com.bjsxt.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class DemoController {

    @RequestMapping("download")
    public void downLoad(String filename, HttpServletResponse resp, HttpServletRequest req) throws IOException {

        // 设置响应流中文件下载
//        resp.setHeader("Content-Disposition", "inline");
        resp.setHeader("Content-Disposition", "attachment;filename=bbb.txt");
        // 创建输出流，把二进制放在响应体中
        ServletOutputStream out = resp.getOutputStream();

        // 创建输入流
        String path = req.getServletContext().getRealPath("file");
        File file = new File(path, filename);

        byte[] bytes = FileUtils.readFileToByteArray(file);
        out.write(bytes);
        out.flush();
        out.close();
    }

    @RequestMapping("upload")
    public String upload(MultipartFile file, String name,HttpServletRequest req) throws IOException {
        String filename = file.getOriginalFilename();

        String suffix = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();


        String path = req.getServletContext().getRealPath("file")+"/"+uuid+suffix;
        System.out.println("bb "+path);
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path));

        return "/index.jsp";


    }

    @RequestMapping("demo")
    public String demo(Model model){
        System.out.println("control的拦截");

        model.addAttribute("model", "我们都爱祖国。");

//        int i = 5/0;
        return "/dinterpector.jsp";
    }

}
