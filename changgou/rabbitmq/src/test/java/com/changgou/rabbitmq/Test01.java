package com.changgou.rabbitmq;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Test01 {

    @Test
    public void test() {
        ConcurrentSkipListMap<String, Integer> cslMap = new ConcurrentSkipListMap<String, Integer>();
        cslMap.put("2017-05-22 16:18:10_key1", 1);
        cslMap.put("2017-05-22 16:18:08_key2", 2);
        cslMap.put("2017-05-22 16:18:20_key3", 1);
        cslMap.put("2017-05-22 16:18:18_key4", 2);
        cslMap.put("2017-05-22 16:18:30_key5", 1);
        cslMap.put("2017-05-22 16:18:28_key2", 2);
        cslMap.put("2017-05-22 16:18:40_key2", 1);
        cslMap.put("2017-05-22 16:18:38_key1", 2);
        cslMap.put("2017-05-22 16:18:59_key1", 2);
        cslMap.put("2017-05-22 17:18:10_key1", 2);
        cslMap.put("2017-05-23 17:18:08_key1", 2);
        cslMap.put("2017-05-24 17:18:08_key1", 2);

        String startKey = "2017-05-22 16:18:08";
        String endKey = "2017-05-22 16:18:30";

        ConcurrentNavigableMap<String, Integer> subMap = cslMap.subMap(startKey, endKey);
        for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key: " + key + ",value: " + value);
        }

        String firstKey = cslMap.firstKey();
        String lastKey = cslMap.lastKey();
        System.out.println(firstKey+":"+cslMap.get(firstKey)+", "+lastKey+":"+cslMap.get(lastKey));
        System.out.println("=================");


        ConcurrentNavigableMap<String, Integer> headMap = cslMap.headMap("2017-05-23");
        for (Map.Entry<String, Integer> entry : headMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }


        // 删除
        for (String key : headMap.keySet()) {
            headMap.remove(key);
        }

        System.out.println("======================");

        // 删除后的集合
        for (Map.Entry<String, Integer> entry : cslMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }




    }

}
