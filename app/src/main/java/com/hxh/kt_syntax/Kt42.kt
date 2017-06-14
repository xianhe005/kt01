package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/13 0013.
 * 委托
 */
// 类委托
// 委托模式已经证明是实现继承的一个很好的替代方式， 而 Kotlin 可以零样板代码地原生支持它。
// 类 Derived 可以继承一个接口 Base4，并将其所有共有的方法委托给一个指定的对象：

interface Base4 {
    fun print()
}

class BaseImpl(val x: Int) : Base4 {
    override fun print() { println(x) }
}

class Derived5(b: Base4) : Base4 by b

class DerivedT(b: Base4) : Base4 by b {
    override fun print() {
        println("abc")
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived5(b).print() // 输出 10
    DerivedT(b).print() // 输出 abc
}
// Derived5 的超类型列表中的 by-子句表示 b 将会在 Derived5 中内部存储。
// 并且编译器将生成转发给 b 的所有 Base4 的方法。
// 请注意，覆盖会以你所期望的方式工作：编译器会使用你的 override 实现取代委托对象中的实现。
// 如果我们为 Derived5 添加 override fun print() { print("abc") }，该程序会输出“abc”而不是“10”。