package com.hxh.kt_syntax

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

/**
 * Created by HXH on 2017/6/12 0012.
 * 嵌套类
 */
// 类可以嵌套在其他类中
class Outer2 {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

val demo = Outer2.Nested().foo() // == 2

// 内部类
// 类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用：
class Outer3 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

val demo2 = Outer3().Inner().foo() // == 1

// 匿名内部类
// 使用对象表达式创建匿名内部类实例：
/*window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ……
    }

    override fun mouseEntered(e: MouseEvent) {
        // ……
    }
})*/

// 如果对象是函数式 Java 接口（即具有单个抽象方法的 Java 接口）的实例，
// 你可以使用带接口类型前缀的lambda表达式创建它：
val listener = View.OnClickListener { println("clicked") }

fun main(args: Array<String>) {
    val tv: TextView? = null
    tv?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("not implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            println(s)
        }
    })
    tv?.setOnClickListener{
        println(it)
    }
    tv?.setOnClickListener(::println)
    tv?.setOnClickListener(listener)
}