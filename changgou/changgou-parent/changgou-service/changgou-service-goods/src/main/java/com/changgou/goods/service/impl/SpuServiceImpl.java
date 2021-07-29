package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void save(Goods goods) {

        Spu spu = goods.getSpu();
        if (goods.getSpu().getId() == null) {
            //新增
            //1.先获取SPU的数据  添加到表中 spu表中
            spu.setId(idWorker.nextId());//生成主键 要唯一 雪花算法.
            spuMapper.insertSelective(spu);

            //新增SKU
        } else {
            //修改
            spuMapper.updateByPrimaryKeySelective(spu);

            //1.先删除spu对应的原来的SKU的类别

            //delete from tb_sku where spu_id = ?
            Sku condition = new Sku();
            condition.setSpuId(spu.getId());
            skuMapper.delete(condition);
            //2.再新增

        }


        //2.获取SKU 的列表数据  循环遍历添加掉sku表中
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            // id 要生成
            sku.setId(idWorker.nextId());
            // name 要生成 (spu的名称+ " "+ 规格的选项的值 )  //  spu的名称: iphonex  规格的数据: spec:{ 颜色:红色,内存大小:16G}
            // 先获取规格的数据
            String spec = sku.getSpec(); //{ 颜色:红色,内存大小:16G}
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            // 转成map对象  key:颜色  value:红色
            String titile = spu.getName();
            for (String key : map.keySet()) {
                // 获取SPU的名称 拼接 即可
                titile += " " + map.get(key);
            }
            sku.setName(titile);

            // create_time
            sku.setCreateTime(new Date());

            // update_time
            sku.setUpdateTime(sku.getCreateTime());

            // spu_id
            sku.setSpuId(spu.getId());

            // category_id 3级分类的ID
            Integer category3Id = spu.getCategory3Id();
            sku.setCategoryId(category3Id);
            // category_name 3级分类的名称
            Category category = categoryMapper.selectByPrimaryKey(category3Id);
            sku.setCategoryName(category.getName());
            // brand_name
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            sku.setBrandName(brand.getName());
            skuMapper.insertSelective(sku);
        }
    }

    @Override
    public void saveGoods(Goods goods) {
        /**
         * 增加spu
         */

        Spu spu = goods.getSpu();
        if(spu.getId() == null){
            // 增加
            spu.setId(idWorker.nextId());
            spuMapper.insertSelective(spu);
        } else {
            // 修改
            spuMapper.updateByPrimaryKeySelective(spu);
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);

        }


        /**
         * 增加sku
         */
        Date date = new Date();
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());

        List<Sku> skus = goods.getSkuList();

        String name = spu.getName();

        for(Sku sku : skus){
            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }

            Map<String,String> map = JSON.parseObject(sku.getSpec(), Map.class);
            for(Map.Entry<String,String> entry : map.entrySet()){
                name += " "+entry.getValue();
            }

            // 设置ID
            sku.setId(idWorker.nextId());
            // 设置supid
            sku.setSpuId(spu.getId());
            //创建日期
            sku.setCreateTime(date);
            // 设置修改日期
            sku.setUpdateTime(date);
            // sku的name
            sku.setName(name);
            // 品牌名称
            sku.setBrandName(brand.getName());
            // 商品分类ID
            sku.setCategoryId(spu.getCategory3Id());
            // 设置分类名称
            sku.setCategoryName(category.getName());


            skuMapper.insertSelective(sku);


        }



    }

    /**
     * Spu条件+分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu){
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     * @param spu
     * @return
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 主键
            if(!StringUtils.isEmpty(spu.getId())){
                    criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                    criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名
            if(!StringUtils.isEmpty(spu.getName())){
                    criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题
            if(!StringUtils.isEmpty(spu.getCaption())){
                    criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(spu.getBrandId())){
                    criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类
            if(!StringUtils.isEmpty(spu.getCategory1Id())){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(spu.getCategory2Id())){
                    criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(spu.getCategory3Id())){
                    criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spu.getTemplateId())){
                    criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id
            if(!StringUtils.isEmpty(spu.getFreightId())){
                    criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片
            if(!StringUtils.isEmpty(spu.getImage())){
                    criteria.andEqualTo("image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                    criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务
            if(!StringUtils.isEmpty(spu.getSaleService())){
                    criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                    criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                    criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                    criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量
            if(!StringUtils.isEmpty(spu.getSaleNum())){
                    criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(spu.getCommentNum())){
                    criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否上架
            if(!StringUtils.isEmpty(spu.getIsMarketable())){
                    criteria.andEqualTo("isMarketable",spu.getIsMarketable());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                    criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否删除
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                    criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态
            if(!StringUtils.isEmpty(spu.getStatus())){
                    criteria.andEqualTo("status",spu.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Spu
     * @param spu
     */
    @Override
    public void update(Spu spu){
        spuMapper.updateByPrimaryKey(spu);
    }

    /**
     * 增加Spu
     * @param spu
     */
    @Override
    public void add(Spu spu){
        spuMapper.insert(spu);
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id){
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }
}
