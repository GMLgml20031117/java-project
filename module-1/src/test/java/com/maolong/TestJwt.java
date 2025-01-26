package com.maolong;

import com.maolong.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TestJwt {
    @Test
    public void testJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "gao");

        String jwt = JwtUtil.createJWT("your-secret-key", 100000L, claims);
        System.out.println(jwt);

        Claims parsedClaims = JwtUtil.parseJWT("your-secret-key", jwt);
        System.out.println(parsedClaims);
        Object username = parsedClaims.get("username");
        Assertions.assertNotNull(username, "Username should not be null");
        System.out.println(username);
    }
}