package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 或者
 */
fun parseInt2(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct2(arg1: String, arg2: String) {
    val x = parseInt2(arg1)
    val y = parseInt2(arg2)

    // ……
    if (x == null) {
        println("Wrong number format in arg1: '${arg1}'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '${arg2}'")
        return
    }

    // 在空检测后，x 和 y 会自动转换为非空值
    println(x * y)
}

fun main(args: Array<String>) {
    printProduct2("6", "7")
    printProduct2("a", "7")
    printProduct2("99", "b")
}