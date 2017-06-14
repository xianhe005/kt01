package com.hxh.kt10.java;

/**
 * Created by HXH on 2017/6/1 0001.
 * 懒加载单例(非线程安全的)
 */

public class LazyNotThreadSafe {
    private static LazyNotThreadSafe INSTANCE;

    private LazyNotThreadSafe() {
    }

    public static LazyNotThreadSafe getInstance() {
        if(INSTANCE == null){
            INSTANCE = new LazyNotThreadSafe();
        }
        return INSTANCE;
    }
}
