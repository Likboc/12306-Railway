package com.example.core;

/**
 * 用户登录容器
 */
public class UserContext {
    private static final ThreadLocal<UserInfoDTO> transmitContext = new ThreadLocal<>();

    public static void setUser(UserInfoDTO user) {
        transmitContext.set(user);
    }

    public static void removeUser() {
        transmitContext.remove();
    }
}
