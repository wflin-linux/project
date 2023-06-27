package com.ccww.aiotlab.common.utils;


import com.ccww.aiotlab.entity.User;

public class UserThreadLocalUtil {

    private UserThreadLocalUtil() {
    }

    // 线程变量隔离
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User sysUser) {
        LOCAL.set(sysUser);
    }

    public static User get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
