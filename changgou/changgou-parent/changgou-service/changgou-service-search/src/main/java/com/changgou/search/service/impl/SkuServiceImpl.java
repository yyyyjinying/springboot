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
        }
        skuEsMapper.saveAll(skuInfos);
    }

    private List<String> getCategoryList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //获取分组结果
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName").size(50));
        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuCategory");

        List<String> categoryList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String categoryName = bucket.getKeyAsString();
            categoryList.add(categoryName);
        }
        return categoryList;
    }

    private List<String> getBrandList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //获取分组结果
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName").size(50));
        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuBrand");

        List<String> brandList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String brandName = bucket.getKeyAsString();
            brandList.add(brandName);
        }
        return brandList;
    }


    private Map<Object, Set<Object>> getSpecList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //获取分组结果
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword").size(10000));
        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuSpec");

        List<String> specList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String specName = bucket.getKeyAsString();
            specList.add(specName);
        }

        Map<Object, Set<Object>> mList = new HashMap<>();

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

    private AggregatedPage<SkuInfo> getSkuPage(NativeSearchQueryBuilder nativeSearchQueryBuilder, Map<String, String> searchMap) {
        //设置高亮条件
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name"));
        HighlightBuilder highlightBuilder = new HighlightBuilder().preTags("<em style=\"color:red\">")
                .postTags("</em>")
                .fragmentSize(100);
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (searchMap != null && searchMap.size() > 0) {
            //1.获取关键字的值

            String keywords = searchMap.get("keywords");

            if (!StringUtils.isEmpty(keywords)) {
                //3.设置查询的条件
//                nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));
                boolQueryBuilder.must(QueryBuilders.matchQuery("name", keywords));
//                boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keywords,"name","brandName","categoryName"));
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

                String[] prices = price.split("_");
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                if (prices.length > 1) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(prices[1])));
                }
            }

        }

        //构建排序查询
        String sortField = searchMap.get("sortField");
        String sortRule = searchMap.get("sortRule");
        if (!StringUtils.isEmpty(sortRule) && !StringUtils.isEmpty(sortField)) {
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortRule.equals("DESC") ? SortOrder.DESC : SortOrder.ASC));
        }

        // 设置分页
        setPageTable(nativeSearchQueryBuilder, searchMap);

        // 构建查询
        NativeSearchQuery query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).build();
        AggregatedPage<SkuInfo> skuPage = elasticsearchTemplate.queryForPage(query, SkuInfo.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                // 存储转化后的高亮数据
                List<T> list = new ArrayList<>();
                // 获取非高亮数据
                for (SearchHit hit : searchResponse.getHits()) {
                    SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);

                    HighlightField name = hit.getHighlightFields().get("name");
                    // 如果有关键字就替换高亮
                    if (name != null && name.getFragments() != null) {
                        Text[] fragments = name.getFragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        for (Text fragment : fragments) {
                            stringBuffer.append(fragment.toString());
                        }
                        skuInfo.setName(stringBuffer.toString());

                    }
                    // 返回搜索的结果
                    list.add((T) skuInfo);
                }
                return new AggregatedPageImpl<T>(list, pageable, searchResponse.getHits().getTotalHits());
            }
        });

        return skuPage;
    }

    // 分页
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
     * http://localhost:18086/search?keywords=华为&spec_网络制式=4G&spec_手机屏幕尺寸=5寸&pageSize=20&sortField=price&sortRule=ASC
     * 网络制式
     *
     * @param searchMap
     * @return
     */
    @Override
    public Map search(Map<String, String> searchMap) {
        //2.创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        AggregatedPage<SkuInfo> skuPage = getSkuPage(nativeSearchQueryBuilder, searchMap);

        Map resultMap = new HashMap<>();


        if (StringUtils.isEmpty(searchMap.get("category"))) {
            // 分类组合搜索
            List<String> categoryList = getCategoryList(nativeSearchQueryBuilder);
            resultMap.put("categoryList", categoryList);
        }
        if (StringUtils.isEmpty(searchMap.get("brand"))) {
            // 品牌组合搜索
            List<String> brandList = getBrandList(nativeSearchQueryBuilder);
            resultMap.put("brandList", brandList);
        }

        Map<Object, Set<Object>> specList = getSpecList(nativeSearchQueryBuilder);

        //6.返回结果

        resultMap.put("specList", specList);
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());

        return resultMap;
    }
}
