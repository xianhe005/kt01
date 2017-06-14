package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用字符串模板
 */
fun main(args: Array<String>) {
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}