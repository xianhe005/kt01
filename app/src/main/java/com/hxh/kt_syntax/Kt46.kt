package com.hxh.kt_syntax

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by HXH on 2017/6/14 0014.
 * 内联函数
 */
// 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。
// 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。

// 但是在许多情况下通过内联化 lambda 表达式可以消除这类的开销。
// 下述函数是这种情况的很好的例子。即 lock() 函数可以很容易地在调用处内联。
// 考虑下面的情况：
fun <T> lock3(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}

fun foo4() {

}

val l = ReentrantLock()
//
val ll = lock3(l) { foo4() }

// 编译器没有为参数创建一个函数对象并生成一个调用。取而代之，编译器可以生成以下代码：
/*l.lock()
try {
    foo4()
}
finally {
    l.unlock()
}*/
// 这个不是我们从一开始就想要的吗？
// 为了让编译器这么做，我们需要使用 inline 修饰符标记 lock() 函数：
inline fun <T> lock2(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}
// inline 修饰符影响函数本身和传给它的 lambda 表达式：所有这些都将内联到调用处。
// 内联可能导致生成的代码增加，但是如果我们使用得当（不内联大函数），
// 它将在性能上有所提升，尤其是在循环中的“超多态（megamorphic）”调用处。

// 禁用内联
// 如果你只想被（作为参数）传给一个内联函数的 lamda 表达式中只有一些被内联，
// 你可以用 noinline 修饰符标记一些函数参数：
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ……
}
// 可以内联的 lambda 表达式只能在内联函数内部调用或者作为可内联的参数传递，
// 但是 noinline 的可以以任何我们喜欢的方式操作：存储在字段中、传送它等等。
// 需要注意的是，如果一个内联函数没有可内联的函数参数并且没有具体化的类型参数，
// 编译器会产生一个警告，因为内联这样的函数很可能并无益处（如果你确认需要内联，则可以关掉该警告）。

// 非局部返回
// 在 Kotlin 中，我们可以只使用一个正常的、非限定的 return 来退出一个命名或匿名函数。
// 这意味着要退出一个 lambda 表达式，我们必须使用一个标签，
// 并且在 lambda 表达式内部禁止使用裸 return，因为 lambda 表达式不能使包含它的函数返回：

/*fun foo() {
    ordinaryFunction {
        return // 错误：不能使 `foo` 在此处返回
    }
}*/
// 但是如果 lambda 表达式传给的函数是内联的，该 return 也可以内联，所以它是允许的：
/*fun foo() {
    inlineFunction {
        return // OK：该 lambda 表达式是内联的
    }
}*/
// 这种返回（位于 lambda 表达式中，但退出包含它的函数）称为非局部返回。
// 我们习惯了在循环中用这种结构，其内联函数通常包含：
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // 从 hasZeros 返回
    }
    return false
}

fun List<Int>.addAllBy(condition: (Int) -> Boolean): Int {
    val iterator = iterator()
    var sum = 0
    iterator.forEach {
        if (condition(it)) sum += it
    }
    return sum
}

// 请注意，一些内联函数可能调用传给它们的不是直接来自函数体、而是来自另一个执行上下文的 lambda 表达式参数，
// 例如来自局部对象或嵌套函数。在这种情况下，该 lambda 表达式中也不允许非局部控制流。
// 为了标识这种情况，该 lambda 表达式参数需要用 crossinline 修饰符标记:
inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ……
}
// break 和 continue 在内联的 lambda 表达式中还不可用，但我们也计划支持它们

fun main(args: Array<String>) {
    val ints = arrayListOf(1, 3, 0, 4, 8, 9)
    println("hasZeros(ints) = ${hasZeros(ints)}")
    println("ints.addAllBy { it % 2 != 0 } = ${ints.addAllBy { it % 2 != 0 }}")
}