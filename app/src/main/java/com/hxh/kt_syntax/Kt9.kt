package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用 if 作为表达式:
 */
fun maxOf2(a: Int, b: Int) = if (a > b) a else b

fun main(args: Array<String>) {
    println("max of 0 and 42 is ${maxOf2(0, 42)}")
}
