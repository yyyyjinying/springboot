package com.cloud.service;

import com.cloud.pojo.User;
import com.cloud.service.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder getPW;

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.changgou.user.pojo.User userInfo = userFeign.testLogin(username);
        if (userInfo == null) {
            return null;
        }

        String password = userInfo.getPassword();

        List<GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User(userInfo.getUsername(), password, authority);
    }
}
