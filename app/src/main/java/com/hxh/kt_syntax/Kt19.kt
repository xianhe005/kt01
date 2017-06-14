package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用区间（range）
 * 使用 in 运算符来检测某个数字是否在指定区间内：
 */
fun main(args: Array<String>) {
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }
}