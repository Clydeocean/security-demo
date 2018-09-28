package com.jsbin.hellospringsecurity.util;

import com.jsbin.hellospringsecurity.domain.Role;
import com.jsbin.hellospringsecurity.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JWTUtils {
    private static String screct = "abckgk";

    public static String generateToken(String username, String roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setExpiration((new Date(System.currentTimeMillis() + 30 * 60 * 1000)))  //30 mins
                .signWith(SignatureAlgorithm.HS256, screct)
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(screct)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static User getUser(String token) {
        log.info("token: {}", token);
        User user = new User();
        user.setUserName(getUsername(token));
        Claims body = Jwts.parser()
                .setSigningKey(screct)
                .parseClaimsJws(token)
                .getBody();
        String roles = body.get("roles", String.class);
        Date expiration = body.getExpiration();
        //Expired
        if ((new Date()).after(expiration)) {
            return null;
        }
        Set<Role> collect = Arrays.stream(roles.split(",")).map(Role::new).collect(Collectors.toSet());
        user.setRoleSet(collect);
        return user;
    }
}
