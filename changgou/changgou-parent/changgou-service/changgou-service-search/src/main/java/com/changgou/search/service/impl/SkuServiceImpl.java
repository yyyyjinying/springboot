package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import entity.Result;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导入sku数据到es
     */
    @Override
    public void importData() {
        //调用changgou-service-goods微服务
//        Result<List<Sku>> skuListResult = skuFeign.findByStatus("1");
        // feign查询，List<Sku>
        Result<List<Sku>> skuListResult = skuFeign.findAll();

        /**
         * 将List<Sku>转化为List<SkuInfo>
         * List<Sku> -> [skuJSON] -> List<SkuInfo>
         */
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(skuListResult.getData()), SkuInfo.class);
        for (SkuInfo skuInfo : skuInfos) {
            Map<String, Object> specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(specMap);
//            skuInfo.setTitleName(skuInfo.getName());
        }
        skuEsMapper.saveAll(skuInfos);
    }

    private void setPageTable(NativeSearchQueryBuilder nativeSearchQueryBuilder, Map<String, String> searchMap) {
        Integer pageNum = 1;
        Integer pageSize = 3;

        if (!StringUtils.isEmpty(searchMap.get("pageNum"))) {
            pageNum = Integer.parseInt(searchMap.get("pageNum"));

        }
        if (!StringUtils.isEmpty(searchMap.get("pageSize"))) {
            pageSize = Integer.parseInt(searchMap.get("pageSize"));
        }

        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum - 1, pageSize));
    }

    /**
     * 数据分组
     * getGroupList
     *
     * @param nativeSearchQueryBuilder
     * @return
     */
    private Map<String, Object> getGroupList(NativeSearchQueryBuilder nativeSearchQueryBuilder, Map<String, String> searchMap) {
        //获取分组结果
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName").size(50));
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName").size(50));
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword").size(10000));

        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);

        StringTerms stringCategoryTerms = aggregatedPage.getAggregations().get("skuCategory");
        StringTerms stringBrandTerms = aggregatedPage.getAggregations().get("skuBrand");
        StringTerms stringSpecTerms = aggregatedPage.getAggregations().get("skuSpec");

        Map<String, Object> resultMap = new HashMap<>();

        // 分类数据
        if (StringUtils.isEmpty(searchMap.get("category"))) {
            resultMap.put("categoryList", getList(stringCategoryTerms));
        }

        // 品牌数据
        if (StringUtils.isEmpty(searchMap.get("brand"))) {
            resultMap.put("brandList", getList(stringBrandTerms));
        }

        // 规格数据
        Map<Object, Set<Object>> specList = getSpecMap(stringSpecTerms);

        //6.返回结果
        resultMap.put("specList", specList);


        return resultMap;
    }

    private BoolQueryBuilder getBoolenQuery(Map<String, String> searchMap) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (searchMap != null && searchMap.size() > 0) {
            //1.获取关键字的值
            String keywords = searchMap.get("keywords");

            if (!StringUtils.isEmpty(keywords)) {
                //3.设置查询的条件
//                nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));
                boolQueryBuilder.must(QueryBuilders.matchQuery("name", keywords));
            }

            // 分类筛选
            if (!StringUtils.isEmpty(searchMap.get("category"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", searchMap.get("category")));
            }

            // 品牌筛选
            if (!StringUtils.isEmpty(searchMap.get("brand"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
            }

            // 规格筛选
            for (String key : searchMap.keySet()) {
                if (key.startsWith("spec_")) {
                    String value = searchMap.get(key);
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword", value));
                }

            }

            // 价格筛选
            String price = searchMap.get("price");
            if (!StringUtils.isEmpty(price)) {

                price = price.replace("元", "").replace("以上", "");

                String[] prices = price.split("_"); // 必须是下划线'_'
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                if (prices.length > 1) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(prices[1])));
                }
            }

        }
        return boolQueryBuilder;
    }

    private List<String> getList(StringTerms stringTerms) {
        List<String> list = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String fieldName = bucket.getKeyAsString();
            list.add(fieldName);
        }
        return list;
    }

    /**
     * 获取spec的数据结构
     *
     * @return
     */
    private Map<Object, Set<Object>> getSpecMap(StringTerms stringSpecTerms) {
        Map<Object, Set<Object>> mList = new HashMap<>();
        List<String> specList = getList(stringSpecTerms);

        for (String item : specList) {
            Map map = JSON.parseObject(item, Map.class);
            for (Object key : map.keySet()) {
                Object value = map.get(key);

                if (mList.containsKey(key)) {
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
        return mList;
    }

    /**
     * http://localhost:18086/search?spec_网络制式=4G&spec_手机屏幕尺寸=5寸&pageSize=20&sortField=price&sortRule=ASC
     * ?keywords=佐丹奴&spec_颜色=蓝色&spec_尺码=26码
     * @param searchMap
     * @return
     */
    @Override
    public Map search(Map<String, String> searchMap) {
        //2.创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //设置高亮条件
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name"));
        HighlightBuilder highlightBuilder = new HighlightBuilder().preTags("<em style=\"color:red\">")
                .postTags("</em>")
                .fragmentSize(100);
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);


        //构建排序查询
        String sortField = searchMap.get("sortField");
        String sortRule = searchMap.get("sortRule");
        if (!StringUtils.isEmpty(sortRule) && !StringUtils.isEmpty(sortField)) {
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortRule.equals("DESC") ? SortOrder.DESC : SortOrder.ASC));
        }

        // 设置分页
        setPageTable(nativeSearchQueryBuilder, searchMap);

        // 构建查询
        NativeSearchQuery query = nativeSearchQueryBuilder.withQuery(getBoolenQuery(searchMap)).build();
        //5.执行查询
//        AggregatedPage<SkuInfo> skuPage = elasticsearchTemplate.queryForPage(query, SkuInfo.class);
        AggregatedPage<SkuInfo> skuPage = elasticsearchTemplate.queryForPage(query, SkuInfo.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                // 存储转化后的高亮数据
                List<T> list = new ArrayList<>();
                // 获取非高亮数据
                for (SearchHit hit : searchResponse.getHits()) {
                    SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);

                    HighlightField name = hit.getHighlightFields().get("name");
                    if (name != null && name.getFragments() != null) {
                        Text[] fragments = name.getFragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        for (Text fragment : fragments) {
                            stringBuffer.append(fragment.toString());
                        }
                        skuInfo.setName(stringBuffer.toString());
                    }
                    list.add((T) skuInfo);
                }

                return new AggregatedPageImpl<T>(list, pageable, searchResponse.getHits().getTotalHits());
            }
        });

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        resultMap.putAll(getGroupList(nativeSearchQueryBuilder, searchMap));

        return resultMap;
    }
}
