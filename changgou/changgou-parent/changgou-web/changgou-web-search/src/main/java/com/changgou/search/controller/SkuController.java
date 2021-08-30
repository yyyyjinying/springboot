package com.changgou.search.controller;

import com.changgou.search.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;

    private void setSearchMapSpecValue(Map searchMap) {
        if (searchMap != null && searchMap.size() > 0) {
            for (Object key : searchMap.keySet()) {
                if (key.toString().startsWith("spec_")) {
                    String value = searchMap.get(key).toString();
                    String replace = value.replace("+", "%2B");
                    searchMap.put(key, replace);
                }
            }
        }
    }

    /**
     * 搜索
     *
     * @param searchMap ?keywords=佐丹奴&spec_颜色=蓝色&spec_尺码=26码
     * @return
     */
    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model, HttpServletRequest httpServletRequest) {
        // 转译特殊字符
        setSearchMapSpecValue(searchMap);

        Map resultMap = skuFeign.search(searchMap);
        model.addAttribute("result", resultMap);
        model.addAttribute("searchMap", searchMap);
        String[] url = url(httpServletRequest);
        model.addAttribute("url", url[0]);
        model.addAttribute("sortUrl", url[1]);

        // 设置分页
        long total = Long.parseLong(resultMap.get("total").toString());
        int pageNumber = Integer.parseInt(resultMap.get("pageNumber").toString()) + 1;
        int pageSize = Integer.parseInt(resultMap.get("pageSize").toString());
        Page<SkuInfo> pageInfo = new Page<>(total, pageNumber, pageSize);
        model.addAttribute("pageInfo", pageInfo);

        return "search";
    }

    private String[] url(HttpServletRequest httpServletRequest) {
        String strurl = "/search/list";
        StringBuilder url = new StringBuilder(strurl);
        StringBuilder sortUrl = new StringBuilder(strurl);
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        if (parameterMap.size() > 0) {
            url.append("?");
            sortUrl.append("?");
            for (String key : parameterMap.keySet()) {
                String value = parameterMap.get(key)[0];

                if (key.startsWith("spec_")) {
                    value = value.replace("+", "%2B");
                }

                if (key.equalsIgnoreCase("pageNum")) {
                    continue;
                }

                url.append(key).append("=").append(value).append("&");
                // sort取最新的值
                if (key.equalsIgnoreCase("sortField") || key.equalsIgnoreCase("sortRule")) {
                    continue;
                }
                sortUrl.append(key).append("=").append(value).append("&");

            }
            return new String[]{
                    url.substring(0, url.length() - 1),
                    sortUrl.substring(0, sortUrl.length() - 1)
            };
        }
        return new String[]{strurl, strurl};
    }
}

