package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * Unit 返回类型可以省略：
 */
fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun main(args: Array<String>) {
    printSum2(-1, 8)
}