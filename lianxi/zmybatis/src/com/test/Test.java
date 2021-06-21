package com.test;


import com.bjsxt.mapper.DynamicMapper;
import com.bjsxt.mapper.PeopleMapper;
import com.bjsxt.pojo.People;
import com.bjsxt.pojo.Student;
import com.bjsxt.pojo.Teacher;
import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {

        SqlSession session = MyBatisUtil.getSession();
//        List<Teacher> list = session.selectList("com.bjsxt.mapper.MultiMapper.selTeacher");
//        System.out.println(list);

//        List<Student> s = session.selectList("com.bjsxt.mapper.StudentMapper.selStudent");
//        System.out.println(s);

        List<Teacher> tList = session.selectList("com.bjsxt.mapper.MultiMapper.selJoin");
        System.out.println(tList);

        List<Teacher> tList1 = session.selectList("com.bjsxt.mapper.MultiMapper.sTeacher");
        System.out.println(tList1);

        session.close();


    }



    public static void test01() throws IOException {
//        Logger logger = Logger.getLogger(Test.class);
//
//        logger.debug("这是一个调试信息");
//        logger.info("欢迎你 ");

//        InputStream is = Resources.getResourceAsStream("mybatis.xml");
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
//        SqlSession session = factory.openSession();
//        List<People> list = session.selectList("com.bjsxt.mapper.PeopleMapper.selAll");
//        List<People> list1 = session.selectList("a.b.d");



//        People p = session.selectOne("com.bjsxt.mapper.PeopleMapper.selById", 1);

//        People people = new People();
//        people.setId(1);
//        People p = session.selectOne("com.bjsxt.mapper.PeopleMapper.sel$ById", people);

//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 1);
//        map.put("name","张三");
//        People p = session.selectOne("com.bjsxt.mapper.PeopleMapper.selMapById", map);


//        System.out.println(p);
//
//        People people = new People();
//        people.setId(2);
//        List<People> listBols = session.selectList("com.bjsxt.mapper.PeopleMapper.selBolId", people);


//        System.out.println(p.toString());
//        for(People item:listBols){
//            System.out.println(item.toString());
//
//        }
//
//        int pageStart = 2;
//        int pageSize = 2;
//        HashMap<String, Object> map1 = new HashMap<>();
//        map1.put("pageStart", (pageStart-1)*pageSize); // #{pageStart},#{pageSize}
//        map1.put("pageSize", pageSize);

//        List<People> list1 = session.selectList("com.bjsxt.mapper.PeopleMapper.selectLimit", map1);
//
//        for(People item:list1){
//            System.out.println(item.toString());
//
//        }

        // 新增
//        People people1 = new People();
//        people1.setName("我你");
//        people1.setAge(45);
//
//        int insert = session.insert("ins", people1);
//        if (insert > 0) {
//            System.out.println("新增成功！");
//
//        }

//
//        People people2 = new People();
//        people2.setId(1);
//        people2.setName("ll");
//
//        int update = session.update("com.bjsxt.mapper.PeopleMapper.update", people2);
//        if (update > 0){
//            System.out.println("update成功");
//
//        }

//        PeopleMapper mapper = session.getMapper(PeopleMapper.class);
//        int update = mapper.update("mmss9s", 5);
//        if(update > 0){
//            System.out.println("unpdate成功");
//
//        }
//
//
//        int delete = session.delete("com.bjsxt.mapper.PeopleMapper.del", 1);
//        if(delete>0){
//            System.out.println("删除成功");
//        }



//        session.commit();

//        DynamicMapper mapper1 = session.getMapper(DynamicMapper.class);
//        List<People> list = mapper1.selectByValues("ff", "45");
//
//        for(People item:list){
//            System.out.println(item.toString());
//
//        }

//        DynamicMapper mapper = session.getMapper(DynamicMapper.class);
//        People people = new People();
//        people.setId(2);
//        people.setName("bca");
//        people.setAge(23);
//        int upd = mapper.upd(people);
//        if(upd > 0){
//            System.out.println("upd成功！");
//
//        }

//        DynamicMapper mapper1 = session.getMapper(DynamicMapper.class);
//        List<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(3);
//        List<People> listPeopls = mapper1.selByDynamic(list);
//
//        for(People item:listPeopls){
//            System.out.println(item.toString());
//
//        }

        /*ArrayList<String> aList = new ArrayList<>();
        aList.add("zhao");
        aList.add("审结");

        int ins = mapper1.ins(aList);
        if(ins > 0){
            System.out.println("insert 成功！");


        }*/




//        session.commit();
//
//        List<People> mTs = session.selectList("com.bjsxt.mapper.MultiMapper.selAll");
//        System.out.println(mTs);

//        session.close();

    }
}
