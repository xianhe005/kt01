package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用 while 循环
 */
fun main(args: Array<String>) {
    val items = listOf("apple", "banana", "kiwi")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}