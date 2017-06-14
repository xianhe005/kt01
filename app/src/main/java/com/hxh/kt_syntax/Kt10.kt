package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用可空值及 null 检测
 * 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
 * 如果 str 的内容不是数字返回 null：
 *
 * 使用返回可空值的函数:
 */
fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 可能会报错，因为他们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 和 y 会自动转换为非空值（non-nullable）
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

fun main(args: Array<String>) {
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")
}
