package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/12 0012.
 * 扩展
 */
// Kotlin 同 C# 和 Gosu 类似，
// 能够扩展一个类的新功能而无需继承该类或使用像装饰者这样的任何类型的设计模式。
// 这通过叫做 扩展 的特殊声明完成。Kotlin 支持 扩展函数 和 扩展属性。

fun main(args: Array<String>) {
    // 现在，我们对任意 MutableList<Int> 调用该函数了：
    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2)
    println(l)
    l.swap2(2, 0)
    println(l)

    // 这个例子会输出 "c"，因为调用的扩展函数只取决于参数 c 的声明类型，该类型是 C3 类。
    printFoo(D())

    C4().foo()

    C5().foo()
    C5().foo(1)

    var c5: C5? = null
    println(c5)
    println(c5.toString())
    c5 = C5()
    println(c5)
    println(c5.toString())

    println(l.lastIndex)
    println(l.mLastIndex)

    val m = MyClass()
    println(m.foo())
    println(MyClass.foo())

    val d2: D2 = D2()
    val c6 = C6()
    c6.caller(d2)

    val c7 = C7()
    println(d2)
    println(c7)
    c7.foo2(d2)

    CC().caller(DD())   // 输出 "DD.foo in CC"
    CC1().caller(DD())  // 输出 "DD.foo in CC1" —— 分发接收者虚拟解析
    CC().caller(DD1())  // 输出 "DD.foo in CC" —— 扩展接收者静态解析
}

// 扩展函数
// 声明一个扩展函数，我们需要用一个 接收者类型 也就是被扩展的类型来作为他的前缀。
// 下面代码为 MutableList<Int> 添加一个swap 函数：
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}
// 这个 this 关键字在扩展函数内部对应到接收者对象（传过来的在点符号前的对象）

// 当然，这个函数对任何 MutableList<T> 起作用，我们可以泛化它：
fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

// 扩展是静态解析的
// 扩展不能真正的修改他们所扩展的类。通过定义一个扩展，你并没有在一个类中插入新成员，
// 仅仅是可以通过该类型的变量用点表达式去调用这个新函数。
// 我们想强调的是扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。
// 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的。例如：

open class C3

class D : C3()

fun C3.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: C3) {
    println(c.foo())
}

// 如果一个类定义有一个成员函数和一个扩展函数，
// 而这两个函数又有相同的接收者类型、相同的名字并且都适用给定的参数，这种情况总是取成员函数。 例如：
class C4 {
    fun foo() {
        println("member")
    }
}

fun C4.foo() {
    println("extension")
}

// 当然，扩展函数重载同样名字但不同签名成员函数也完全可以：
class C5 {
    fun foo() {
        println("member")
    }
}

fun C5.foo(i: Int) {
    println("extension")
}
// 调用 C5().foo(1) 将输出 "extension"。

// 可空接收者
// 注意可以为可空的接收者类型定义扩展。
// 这样的扩展可以在对象变量上调用， 即使其值为 null，并且可以在函数体内检测 this == null，
// 这能让你在没有检测 null 的时候调用 Kotlin 中的toString()：检测发生在扩展函数的内部。
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

// 扩展属性
// 和函数类似，Kotlin 支持扩展属性：
val <T> List<T>.lastIndex: Int
    get() = size - 1
val <T> List<T>.mLastIndex: Int
    get() {
        return this.lastIndex + 1
    }
// 注意：由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。
// 这就是为什么扩展属性不能有初始化器。他们的行为只能由显式提供的 getters/setters 定义。
// 例如:
// val C5.bar = 1 // 错误：扩展属性不能有初始化器

// 伴生对象的扩展
// 如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数和属性：

class MyClass {
    companion object {}  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    println("MyClass.Companion.foo()")
}

// 就像伴生对象的其他普通成员，只需用类名作为限定符去调用他们
fun MyClass.foo() {
    println("MyClass.foo()")
}

// 扩展的作用域
// 大多数时候我们在顶层定义扩展，即直接在包里：
/*package foo.bar
fun Baz.goo() { …… }*/
// 要使用所定义包之外的一个扩展，我们需要在调用方导入它：
/*package com.example.usage
import foo.bar.goo // 导入所有名为“goo”的扩展
// 或者
import foo.bar.*   // 从“foo.bar”导入一切
fun usage(baz: Baz) {
    baz.goo()
}*/

// 扩展声明为成员
// 在一个类内部你可以为另一个类声明扩展。在这样的扩展内部，有多个 隐式接收者 —— 其中的对象成员可以无需通过限定符访问。
// 扩展声明所在的类的实例称为 分发接收者，扩展方法调用所在的接收者类型的实例称为 扩展接收者 。

class D2 {
    fun bar() {
        println("D2 bar()")
    }
}

class C6 {
    fun baz() {
        println("C6 baz()")
    }

    fun D2.foo() {
        bar()   // 调用 D2.bar
        baz()   // 调用 C6.baz
    }

    fun caller(d: D2) {
        d.foo()   // 调用扩展函数
    }
}

// 对于分发接收者和扩展接收者的成员名字冲突的情况，扩展接收者优先。要引用分发接收者的成员你可以使用 限定的 this 语法。
class C7 {
    fun D2.foo() {
        println(toString())         // 调用 D.toString()
        println(this@C7.toString())  // 调用 C.toString()
    }

    fun foo2(d: D2) {
        d.foo()
    }
}

// 声明为成员的扩展可以声明为 open 并在子类中覆盖。
// 这意味着这些函数的分发对于分发接收者类型是虚拟的，但对于扩展接收者类型是静态的。
open class DD {
}

class DD1 : DD() {
}

open class CC {
    open fun DD.foo() {
        println("DD.foo in CC")
    }

    open fun DD1.foo() {
        println("DD1.foo in CC")
    }

    fun caller(d: DD) {
        d.foo()   // 调用扩展函数
    }
}

class CC1 : CC() {
    override fun DD.foo() {
        println("DD.foo in CC1")
    }

    override fun DD1.foo() {
        println("DD1.foo in CC1")
    }
}

// 动机
// 在Java中，我们将类命名为“*Utils”：FileUtils、StringUtils 等，著名的 java.util.Collections 也属于同一种命名方式。
// 关于这些 Utils-类的不愉快的部分是代码写成这样：

// Java
// Collections.swap(list, Collections.binarySearch(list, Collections.max(otherList)), Collections.max(list))
// 这些类名总是碍手碍脚的，我们可以通过静态导入达到这样效果：

// Java
// swap(list, binarySearch(list, max(otherList)), max(list))
// 这会变得好一点，但是我们并没有从 IDE 强大的自动补全功能中得到帮助。如果能这样就更好了：

// Java
// list.swap(list.binarySearch(otherList.max()), list.max())
// 但是我们不希望在 List 类内实现这些所有可能的方法，对吧？这时候扩展将会帮助我们。