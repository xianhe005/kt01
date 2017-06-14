package com.hxh.kt10.kotlin

import android.widget.TextView
import com.hxh.kt10.kotlin.PlainOldSingleton.sayHello

/**
 * Created by HXH on 2017/6/1 0001.
 *
 */
class LazyNotThreadSafe {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            LazyNotThreadSafe()
        }

        //下面是另一种等价的写法, 获取单例使用 get 方法
        private var instance2: LazyNotThreadSafe? = null

        fun get(): LazyNotThreadSafe {
            if (instance2 == null) {
                instance2 = LazyNotThreadSafe()
            }
            return instance2!!
        }
    }
}

fun main(args: Array<String>) {
    LazyNotThreadSafe.instance.sayHello()
    LazyNotThreadSafe.get().sayHello()
}

fun LazyNotThreadSafe.sayHello() {
    println("hello")
}