package com.hxh.kt10.java;

/**
 * Created by HXH on 2017/6/1 0001.
 * 懒加载单例(线程安全的-方法同步)
 */

public class LazyThreadSafeSynchronized {
    private static LazyThreadSafeSynchronized INSTANCE;

    private LazyThreadSafeSynchronized() {

    }

    public static synchronized LazyThreadSafeSynchronized getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyThreadSafeSynchronized();
        }
        return INSTANCE;
    }
}
