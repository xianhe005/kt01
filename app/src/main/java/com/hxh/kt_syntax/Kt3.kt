package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 函数返回无意义的值：
 */
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun main(args: Array<String>) {
    printSum(-1, 8)
}