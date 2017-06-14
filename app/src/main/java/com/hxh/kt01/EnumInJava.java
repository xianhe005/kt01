package com.hxh.kt01;

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */

public class EnumInJava {
    public static void main(String... args) {
        Lang lang = Lang.ENGLISH;
        String bye;
        switch (lang) {
            case ENGLISH:
                bye = "Bye";
                break;
            case CHINESE:
                bye = "再见";
                break;
            case JAPANESE:
                bye = "さようなら";
                break;
            case KOREAN:
                bye = "안녕히가세요";
                break;
            default:
                bye = "Bye";
        }
        System.out.println(bye);
    }
}
