package com.zhao.demo.mapper;
import com.zhao.demo.bean.Muser;
import com.zhao.demo.bean.User;
import com.zhao.demo.bean.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UsersMapper usersMapper;

    @Test
    public void selectAllUser() {
        List<User> users = userMapper.selectAllUser();
        for(User user:users){
            System.out.println(user);

        }
    }

    @Test
    public void selByUsers(){
        Users users = new Users();

        users.setUsername("zhao");
        users.setPassword("123");

        Users listUsers = usersMapper.selByUsers(users);
        System.out.println(listUsers);
        log.debug("赵晋英");
    }

    @Autowired
    private MuserMapper muserMapper;

    @Test
    public void testSelect() {
        List<Muser> musers = muserMapper.selectList(null);
        Assert.assertEquals(5,musers.size());
        musers.forEach(System.out::println); // 函数式编程
    }

}
