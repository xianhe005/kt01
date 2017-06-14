package com.hxh.kt10.kotlin

/**
 * Created by HXH on 2017/6/1 0001.
 *
 */
class LazyThreadSafeDoubleCheck private constructor() {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            LazyThreadSafeDoubleCheck()
        }

        private @Volatile var instance2: LazyThreadSafeDoubleCheck? = null

        fun get():LazyThreadSafeDoubleCheck{
            if (instance2 == null) {
                synchronized(this) {
                    if (instance2 == null) {
                        instance2 = LazyThreadSafeDoubleCheck()
                    }
                }
            }
            return instance2!!
        }
    }
}

fun main(args: Array<String>) {
    LazyThreadSafeDoubleCheck.instance.sayHello()
    LazyThreadSafeDoubleCheck.get().sayHello()
}

fun LazyThreadSafeDoubleCheck.sayHello(){
    println("hello")
}