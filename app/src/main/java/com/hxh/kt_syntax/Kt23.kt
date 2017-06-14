package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用集合
 * 对集合进行迭代:
 */
fun main(args: Array<String>) {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
}