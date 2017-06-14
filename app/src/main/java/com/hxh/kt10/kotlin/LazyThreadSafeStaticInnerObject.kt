package com.hxh.kt10.kotlin

/**
 * Created by HXH on 2017/6/1 0001.
 *
 */
class LazyThreadSafeStaticInnerObject private constructor() {

    private object Holder {
        val instance = LazyThreadSafeStaticInnerObject()
    }

    companion object {
        fun getInstance() = Holder.instance
    }
}

fun main(args: Array<String>) {
    LazyThreadSafeStaticInnerObject.getInstance().sayHello()
}

fun LazyThreadSafeStaticInnerObject.sayHello() {
    println("hello")
}