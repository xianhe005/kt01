package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 定义函数
 * 带有两个 Int 参数、返回 Int 的函数：
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun main(args: Array<String>) {
    print("sum of 3 and 5 is ")
    println(sum(3, 5))
}
