package com.changgou.goods.service.impl;

import jedis.cluster.JedisClusterDao;
import com.changgou.goods.dao.RimgMapper;
import com.changgou.goods.pojo.Rimg;
import com.changgou.goods.service.RimgService;
import entity.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RimgServiceImpl implements RimgService {
    @Autowired
    RimgMapper rimgMapper;

    @Autowired
    JedisClusterDao jedisClusterDao;

    public List<Rimg> selectAll() {
        //  判断redis中是否存在指定key
        if (jedisClusterDao.exists("bigimg")) {
            String value = jedisClusterDao.get("bigimg");
            if (value != null && !value.equals("")) {
                return JsonUtils.jsonToList(value, Rimg.class);
            }
        }

        // 如果不存在,从mysql中取出
        List<Rimg> list = rimgMapper.selectAll();
        jedisClusterDao.set("bigimg", JsonUtils.objectToJson(list));
        return list;
    }

    @Override
    public Integer del(Integer id) {
        int index = rimgMapper.deleteByPrimaryKey(id);
        if (index > 0) {
            if (jedisClusterDao.exists("bigimg")) {
                String values = jedisClusterDao.get("bigimg");
                List<Rimg> list = JsonUtils.jsonToList(values, Rimg.class);
                Rimg exist = null;
                for (Rimg item : list) {
                    if ((int) item.getId() == id) {
                        exist = item;
                    }
                }
                list.remove(exist);

                jedisClusterDao.set("bigimg", JsonUtils.objectToJson(list));
            }
            return id;
        }
        return -1;
    }
}
