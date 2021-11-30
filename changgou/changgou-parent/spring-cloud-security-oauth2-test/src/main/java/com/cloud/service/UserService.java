package com.cloud.service;

import com.cloud.pojo.User;
import com.cloud.service.feign.UserFeign;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder getPW;

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 认证用户是否存在
        com.changgou.user.pojo.User userInfo = userFeign.testLogin(username);
        if (userInfo == null) {
            return null;
        }

        // 获取用户权限标示
        Result<List<String>> rList = userFeign.permissionByUsername(userInfo.getUsername());
        List<String> codeData = rList.getData();
        String authorityString = StringUtils.join(codeData, ",");

        List<String> roleByUsername = userFeign.getRoleByUsername(userInfo.getUsername());
        String roleString = StringUtils.join(roleByUsername, ",");

        log.info(authorityString +","+roleString);

        List<GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString +","+roleString);
        return new User(userInfo.getUsername(), userInfo.getPassword(), authority);
    }
}
