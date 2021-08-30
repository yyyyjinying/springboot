package com.changgou.search.controller;

import com.changgou.search.feign.SkuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
@RequestMapping(value = "/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;

    /**
     * 搜索
     *
     * @param searchMap ?keywords=佐丹奴&spec_颜色=蓝色&spec_尺码=26码
     * @return
     */
    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model) {
        Map resultMap = skuFeign.search(searchMap);
        model.addAttribute("result", resultMap);
        model.addAttribute("searchMap", searchMap);
        model.addAttribute("url", url(searchMap));
        return "search";
    }

    private String url(Map<String, String> searchMap) {
        String url = "/search/list";
        if (searchMap != null && searchMap.size() > 0) {
            url += "?";
            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                //如果是排序 则 跳过 拼接排序的地址 因为有数据
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();

                if (key.equalsIgnoreCase("sortField") || key.equalsIgnoreCase("sortRule")) {
                    continue;
                }
                url += key + "=" + value + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}

