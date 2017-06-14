package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/5 0005.
 * 冒号
 * 类型和超类型之间的冒号前要有一个空格，而实例和类型之间的冒号前不要有空格：
 */
interface Foo<out T : Any> {
    fun foo(a: Int): T
}

fun main(args: Array<String>) {
    // Lambda表达式
    // 在lambda表达式中, 大括号左右要加空格，分隔参数与代码体的箭头左右也要加空格 。
    // lambda表达应尽可能不要写在圆括号中
    // 在非嵌套的短lambda表达式中，最好使用约定俗成的默认参数 it 来替代显式声明参数名 。
    // 在嵌套的有参数的lambda表达式中，参数应该总是显式声明。
    val list = listOf(1, 6, 13, 84)
    list.filter { it > 10 }.map { it * 2 }.forEach({ println(it) })
}

// 类头格式化
// 有少数几个参数的类可以写成一行：
open class Person(id: Int, name: String)

// 具有较长类头的类应该格式化，以使每个主构造函数参数位于带有缩进的单独一行中。
// 此外，右括号应该另起一行。如果我们使用继承，那么超类构造函数调用或者实现接口列表
// 应位于与括号相同的行上：
class Person2(
        id: Int,
        name: String,
        surname: String
) : Foo<Person> {
    override fun foo(a: Int): Person {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

// 对于多个接口，应首先放置超类构造函数调用，然后每个接口应位于不同的行中：
// 构造函数参数可以使用常规缩进或连续缩进（双倍的常规缩进）。
class Person3(
        id: Int,
        name: String,
        surname: String
) : Person(id, name),
        Foo<Person> {
    override fun foo(a: Int): Person {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

// Unit
// 如果函数返回 Unit 类型，该返回类型应该省略：
fun foo() { // 省略了 ": Unit"

}