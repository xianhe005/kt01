package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/12 0012.
 * 接口
 */
// Kotlin 的接口与 Java 8 类似，既包含抽象方法的声明，也包含实现。
// 与抽象类不同的是，接口无法保存状态。它可以有属性但必须声明为抽象或提供访问器实现。
// 使用关键字 interface 来定义接口
interface MyInterface {
    fun bar()
    fun foo() {
        // 可选的方法体
    }
}

// 实现接口
// 一个类或者对象可以实现一个或多个接口。
class Child : MyInterface {
    override fun bar() {
        // 方法体
    }
}

// 接口中的属性
// 你可以在接口中定义属性。在接口中声明的属性要么是抽象的，要么提供访问器的实现。
// 在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们。
interface MyInterface2 {
    val prop: Int // 抽象的

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child2 : MyInterface2 {
    override val prop: Int = 29
}

// 解决覆盖冲突
// 实现多个接口时，可能会遇到同一方法继承多个实现的问题。例如

interface A1 {
    fun foo() {
        print("A1")
    }

    fun bar()
}

interface B1 {
    fun foo() {
        print("B1")
    }

    fun bar() {
        print("bar")
    }
}

class C1 : A1 {
    override fun bar() {
        print("bar")
    }
}

class D1 : A1, B1 {
    override fun foo() {
        super<A1>.foo()
        super<B1>.foo()
    }

    override fun bar() {
        super<B1>.bar()
    }
}
// 上例中，接口 A1 和 B1 都定义了方法 foo() 和 bar()。
// 两者都实现了 foo(), 但是只有 B1 实现了 bar() (bar() 在 A1 中没有标记为抽象， 因为没有方法体时默认为抽象）。
// 因为 C1 是一个实现了 A1 的具体类，所以必须要重写 bar() 并实现这个抽象方法。
// 然而，如果我们从 A1 和 B1 派生 D1，我们需要实现我们从多个接口继承的所有方法，
// 并指明 D1应该如何实现它们。这一规则既适用于继承单个实现（bar()）的方法也适用于继承多个实现（foo()）的方法。