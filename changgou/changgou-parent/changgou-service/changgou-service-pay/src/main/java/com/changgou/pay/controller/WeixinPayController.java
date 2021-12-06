package com.changgou.pay.controller;


import com.changgou.pay.service.WeixinPayService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/weixin/pay")
@CrossOrigin
public class WeixinPayController {

    @Autowired
    private WeixinPayService weixinPayService;
    /***
     * 创建二维码
     * @return
     */
    @RequestMapping(value = "/create/native")
    public Result createNative(String outtradeno, String money){
        Map<String,String> resultMap = weixinPayService.createNative(outtradeno,money);
        return new Result(true, StatusCode.OK,"创建二维码预付订单成功！",resultMap);
    }
}
