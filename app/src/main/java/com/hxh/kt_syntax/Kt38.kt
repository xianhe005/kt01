package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/12 0012.
 * 泛型
 */

// 与 Java 类似，Kotlin 中的类也可以有类型参数：
class Box<T>(t: T) {
    var value = t
}

fun main(args: Array<String>) {
    // 一般来说，要创建这样类的实例，我们需要提供类型参数：

    val box: Box<Int> = Box<Int>(1)
    // 但是如果类型参数可以推断出来，例如从构造函数的参数或者从其他途径，允许省略类型参数：
    val box2 = Box(1) // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。
    println(box.value)
    println(box2.value)
}
//[TODO] 型变