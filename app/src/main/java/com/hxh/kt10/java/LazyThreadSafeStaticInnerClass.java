package com.hxh.kt10.java;

/**
 * Created by HXH on 2017/6/1 0001.
 * 懒加载单例(线程安全的-静态内部类方式)
 */

public class LazyThreadSafeStaticInnerClass {
    private static class Holder {
        private static LazyThreadSafeStaticInnerClass INSTANCE = new LazyThreadSafeStaticInnerClass();
    }

    private LazyThreadSafeStaticInnerClass() {

    }

    public LazyThreadSafeStaticInnerClass getInstance() {
        return Holder.INSTANCE;
    }
}
