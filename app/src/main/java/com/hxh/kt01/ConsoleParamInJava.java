package com.hxh.kt01;

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */

public class ConsoleParamInJava {

    public static void main(String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
