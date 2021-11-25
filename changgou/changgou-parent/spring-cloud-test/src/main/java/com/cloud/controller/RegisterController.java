package com.cloud.controller;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@Slf4j
public class RegisterController {

    @Value("${mail.fromMail.sender}")
    private String sender;// 发送者

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private Map<String, Object> resultMap = new HashMap<>();

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    private String VerifyCode(int n){
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        return sb.toString();
    }

    //保存验证码和时间
    private void saveCode(String code){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
        String hash = SecureUtil.md5(code); //生成MD5值
        resultMap.put("hash", hash);
        resultMap.put("tamp", currentTime);
    }

    @RequestMapping("/sendEmail")
    //转换json数据  @ResponseBody
    @ResponseBody
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String code = VerifyCode(6);    //随机数生成6位验证码
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("博客系统");// 标题
        message.setText("【博客系统】你的验证码为："+code+"，有效时间为5分钟(若不是本人操作，可忽略该条邮件)");// 内容
        try {
            javaMailSender.send(message);
            log.info("文本邮件发送成功！");
            saveCode(code);
            return "success";
        }catch (MailSendException e){
            log.error("目标邮箱不存在");
            return "false";
        } catch (Exception e) {
            log.error("文本邮件发送异常！", e);
            return "failure";
        }
    }

}
