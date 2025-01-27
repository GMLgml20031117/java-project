package com.maolong.common.util;


import org.springframework.stereotype.Component;


public class BaseContext {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static void setCurrentUserId(Integer id) {
        threadLocal.set(id);
    }
    public static Integer getCurrentUserId() {
        return threadLocal.get();
    }
    public static void removeCurrentUserId() {
        threadLocal.remove();
    }
}
