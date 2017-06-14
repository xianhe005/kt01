package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 将表达式作为函数体、返回值类型自动推断的函数：
 */
fun sum2(a: Int, b: Int) = a + b

fun main(args: Array<String>) {
    println("sum of 19 and 23 is ${sum2(19, 23)}")
}