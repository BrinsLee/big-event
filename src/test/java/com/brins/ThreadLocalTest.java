package com.brins;

import org.junit.jupiter.api.Test;

/**
 * Created by lipeilin on 2024/10/9.
 */
public class ThreadLocalTest {

    @Test
    public void testThreadLocal() {
        ThreadLocal tl = new ThreadLocal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("晓燕");
                System.out.println(Thread.currentThread().getName() + tl.get());
            }
        }, "线程一").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("姚晨");
                tl.set("辣辣");
                System.out.println(Thread.currentThread().getName() + tl.get());
            }
        }, "线程二").start();
    }
}
