package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 使用 lambda 表达式来过滤（filter）和映射（map）集合：
 */
fun main(args: Array<String>) {
    val fruits = listOf("banana", "avocado", "apple", "kiwi")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
}