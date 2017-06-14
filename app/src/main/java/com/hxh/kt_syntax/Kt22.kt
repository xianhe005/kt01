package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 或数列迭代：
 */
fun main(args: Array<String>) {
    for (x in 1..10 step 2) {
        print(x)
    }
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}