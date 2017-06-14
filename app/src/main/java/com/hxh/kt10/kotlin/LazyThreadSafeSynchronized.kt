package com.hxh.kt10.kotlin

/**
 * Created by HXH on 2017/6/1 0001.
 *
 */
class LazyThreadSafeSynchronized private constructor() {
    companion object {
        private var instance: LazyThreadSafeSynchronized? = null

        fun get(): LazyThreadSafeSynchronized {
            if (instance == null) instance = LazyThreadSafeSynchronized()
            return instance!!
        }
    }
}

fun main(args: Array<String>) {
    LazyThreadSafeSynchronized.get().sayHello()
}

fun LazyThreadSafeSynchronized.sayHello() {
    println("hello")
}