package com.cloud.service;

import com.cloud.pojo.User;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password = getPW.encode("123456");

        List<GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User("admin",password,authority);
    }
}
