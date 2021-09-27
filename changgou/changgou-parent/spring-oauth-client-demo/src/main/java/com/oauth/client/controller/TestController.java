package com.oauth.client.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class TestController {

    @RequestMapping("/info")
    public Object getInfo(Authentication authentication) {
        Object details = authentication.getDetails();
//        Object principal = authentication.getPrincipal();
//        Object credentials = authentication.getCredentials();

        return details;
//        String header = httpServletRequest.getHeader("Authorization");
//        // bearer最后一次出现的索引 + 7
//        String token = header.substring(header.lastIndexOf("bearer") + 7);
//
//        return Jwts.parser()
//                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(details.tokenValue)
//                .getBody();
    }

}
