package com.changgou.goods.dao;
import com.changgou.goods.pojo.Category;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:Category的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface CategoryMapper extends Mapper<Category> {

//    @Results(value={
//            @Result(column = "id", property = "id", id = true),
//            @Result(column = "name", property = "name"),
//            @Result(column = "pid", property = "pid"),
//            @Result(property = "children", many=@Many(select="selByPid"), column = "{uid=uid,pid=id}")
//
//    })
//    @Select("select *,#{uid} uid from menu where id in (select mid from users_menu where uid=#{uid}) and pid=#{pid}")
//    List<Menu> selByPid(Map<String, Object> map);

    @Results(value={
            @Result(column = "id", property = "id", id = true),
            @Result(column = "parent_id", property = "parentId"),
            @Result(property = "children", many=@Many(select="selByPid"), column = "id")

    })
//    @Select("select id,name,parent_id from tb_category where parent_id=#{id}")
    @Select("select * from tb_category where parent_id=#{id}")
    List<Category> selByPid(int id);

    @Results(value={
            @Result(column="id",property = "id",id=true),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "goods_num", property = "goodsNum"),
            @Result(column = "is_show", property = "isShow"),
            @Result(column = "is_menu", property = "isMenu"),
            @Result(column = "seq", property = "seq"),
            @Result(column = "template_id", property="templateId"),
            @Result(column = "name", property = "name")
    })
    @Select("select * from tb_category where parent_id=#{id}")
    List<Category> selectByPid(int id);
}
