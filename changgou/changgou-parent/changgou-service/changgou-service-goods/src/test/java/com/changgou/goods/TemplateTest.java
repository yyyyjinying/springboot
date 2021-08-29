package com.changgou.goods;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.impl.TemplateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
public class TemplateTest {

    @Autowired
    TemplateServiceImpl templateService;

    @Test
    public void findByCategoryId(){
        Template byCategoryId = templateService.findByCategoryId(560);
        log.info("zjy-"+byCategoryId);
    }

    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("{'颜色': '黑色', '尺码': '38'}");
        list.add("{'尺码': '43'}");
        list.add("{}");

        Map<Object,Set<Object>> mList = new HashMap<>();

        for(String item:list){
            Map map = JSON.parseObject(item, Map.class);
            for (Object key : map.keySet()) {
                Object value = map.get(key);

                if(mList.containsKey(key)){
                    Set<Object> set = mList.get(key);
                    set.add(value);
                    mList.put(key, set);
                } else {
                    Set set = new HashSet();
                    set.add(value);
                    mList.put(key, set);
                }
            }
        }

        System.out.println(mList);

    }
}
