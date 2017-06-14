package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用 in 运算符来判断集合内是否包含某实例：
 */
fun main(args: Array<String>) {
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}