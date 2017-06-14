package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 可变变量：
 */
fun main(args: Array<String>) {
    var x = 5 // 自动推断出 `Int` 类型
    x += 1
    println("x = $x")
}

// 这是一个行注释

/* 这是一个多行的
   块注释。 */
//与 Java 不同的是，Kotlin 的块注释可以嵌套。