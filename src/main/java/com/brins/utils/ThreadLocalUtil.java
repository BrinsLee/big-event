package com.brins.utils;

/**
 * Created by lipeilin on 2024/1/24.
 */
public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
