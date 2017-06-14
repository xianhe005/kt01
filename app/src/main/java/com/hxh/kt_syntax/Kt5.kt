package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 定义局部变量
 * 一次赋值（只读）的局部变量:
 */
fun main(args: Array<String>) {
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    c = 3       // 明确赋值
    println("a = $a, b = $b, c = $c")
}