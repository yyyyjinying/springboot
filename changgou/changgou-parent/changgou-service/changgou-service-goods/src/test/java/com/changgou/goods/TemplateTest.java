package com.changgou.goods;

import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.impl.TemplateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TemplateTest {

    @Autowired
    TemplateServiceImpl templateService;

    @Test
    public void findByCategoryId(){
        Template byCategoryId = templateService.findByCategoryId(560);
        log.info("zjy-"+byCategoryId);
    }
}
