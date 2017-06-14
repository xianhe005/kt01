package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/6 0006.
 * 控制流
 */
fun main(args: Array<String>) {
    // If表达式
    // 在 Kotlin 中，if是一个表达式，即它会返回一个值。
    // 因此就不需要三元运算符（条件 ? 然后 : 否则），因为普通的 if 就能胜任这个角色。
    val a = 1
    val b = 2
    // 传统用法
    var max = a
    if (a < b) max = b

    // With else
    val max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }

    // 作为表达式
    val max3 = if (a > b) a else b

    //if的分支可以是代码块，最后的表达式作为该块的值：
    val max4 = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }
    println(max4)
    //如果你使用 if 作为表达式而不是语句（例如：返回它的值或者 把它赋给变量），该表达式需要有 else 分支。

    // When 表达式
    // when 取代了类 C 语言的 switch 操作符。其最简单的形式如下：
    when (a) {
        1 -> println("a == 1")
        2 -> println("a == 2")
        else -> { // 注意这个块
            println("a is neither 1 nor 2")
        }
    }

    /*when 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。
    when 既可以被当做表达式使用也可以被当做语句使用。
    如果它被当做表达式， 符合条件的分支的值就是整个表达式的值，如果当做语句使用， 则忽略个别分支的值。
    （像 if 一样，每一个分支可以是一个代码块，它的值 是块中最后的表达式的值。）
    如果其他分支都不满足条件将会求值 else 分支。 如果 when 作为一个表达式使用，则必须有 else 分支，
    除非编译器能够检测出所有的可能情况都已经覆盖了。*/

    //如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔：
    when (a) {
        0, 1 -> println("a == 0 or a == 1")
        else -> println("otherwise")
    }

    // 我们可以用任意表达式（而不只是常量）作为分支条件
    val x = 1
    val s = "1"
    when (x) {
        parseInt(s) -> println("s encodes x")
        else -> println("s does not encode x")
    }

    // 我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中：
    val validNumbers = listOf(2, 3, 19)
    when (x) {
        in 2..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }

    // 另一种可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。
    // 注意： 由于智能转换，你可以访问该类型的方法和属性而无需 任何额外的检测。
    fun hasPrefix(x: Any?) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }
    println(hasPrefix("prefix"))

    // when 也可以用来取代 if-else if链。 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支：
    val xx = X(true, true)
    when {
        xx.isOdd() -> println("xx is odd")
        xx.isEven() -> println("xx is even")
        else -> println("xx is funny")
    }

    // For 循环
    // for 循环可以对任何提供迭代器（iterator）的对象进行遍历，语法如下:

    val collection = arrayOf("1","sss")
    for (item in collection) println(item)

    //循环体可以是一个代码块。
    val ints = arrayOf(1,2,3,4)
    for (item: Int in ints) {
        // ……
        println(item)
    }

    /*如上所述，for 可以循环遍历任何提供了迭代器的对象。即：
    有一个成员函数或者扩展函数 iterator()，它的返回类型
    有一个成员函数或者扩展函数 next()，并且
    有一个成员函数或者扩展函数 hasNext() 返回 Boolean。
    这三个函数都需要标记为 operator。*/

    // 对数组的 for 循环会被编译为并不创建迭代器的基于索引的循环。
    // 如果你想要通过索引遍历一个数组或者一个 list，你可以这么做：

    val array = arrayOf(1,4,44)//listOf(1,3,77)
    for (i in array.indices) {
        println(array[i])
    }
    // 注意这种“在区间上遍历”会编译成优化的实现而不会创建额外对象。
    // 或者你可以用库函数 withIndex：
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }
}

class X(var old: Boolean, val even: Boolean) {
    fun isOdd(): Boolean {
        return old
    }

    fun isEven(): Boolean {
        return even;
    }
}