package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 检测某个数字是否在指定区间外:
 */
fun main(args: Array<String>) {
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range too")
    }
}
