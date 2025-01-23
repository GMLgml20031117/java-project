package com.maolong.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJwt(String secretKey,Long ttlMillis, Map<String, Object> claims){
        //指定签名算法
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;

        //生成JWT的时间
        long expMillis = System.currentTimeMillis();
        Date exp = new Date(expMillis + ttlMillis);

        //设置jwt的body
        String compact = Jwts.builder()
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey)
                //设置过期时间
                .setExpiration(exp)
                .compact();
        return compact;
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJwt(String secretKey,String token){
        Claims body = Jwts.parser()
                //设置签名密钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                //需要解析的token
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

}
