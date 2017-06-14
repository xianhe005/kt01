package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用条件表达式
 */
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun main(args: Array<String>) {
    println("max of 0 and 42 is ${maxOf(0, 42)}")
}