package com.hxh.kt10.java;

/**
 * Created by HXH on 2017/6/1 0001.
 * 普通的单例
 */

public class PlainOldSingleton {

    private static PlainOldSingleton INSTANCE = new PlainOldSingleton();

    private PlainOldSingleton() {
        System.out.println("PlainOldSingleton");
    }

    public static PlainOldSingleton getInstance() {
        return INSTANCE;
    }
}
