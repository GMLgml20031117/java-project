package com.maolong;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class TestMD5 {
    @Test
    public void testMD5(){
        String password="123";
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(s);
    }
}
