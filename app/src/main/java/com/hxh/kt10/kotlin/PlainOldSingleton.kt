package com.hxh.kt10.kotlin

/**
 * Created by HXH on 2017/6/1 0001.
 *
 */
object PlainOldSingleton {
    fun sayHello() {
        println("hello")
    }
}

fun main(args: Array<String>) {
    val s: PlainOldSingleton = PlainOldSingleton;
    s.sayHello()
}


