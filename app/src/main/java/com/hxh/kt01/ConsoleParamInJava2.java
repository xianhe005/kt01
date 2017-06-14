package com.hxh.kt01;

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */

public class ConsoleParamInJava2 {
    public static void main(String... args) {
        for (String arg : args) {
            String[] splits = arg.split("_");
            for (String split : splits) {
                System.out.print(split + " ");
            }
        }
    }
}
