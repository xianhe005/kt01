package com.hxh.kt_syntax

import javax.inject.Inject

/**
 * Created by HXH on 2017/6/9 0009.
 * 属性和字段
 */

fun main(args: Array<String>) {
    val address = Address()
    val address2 = copyAddress(address)
    println(address)
    println(address2)

    val test2Class = Test2Class()
    println(test2Class.counter)
    test2Class.counter = -100
    println(test2Class.counter)
    test2Class.counter = 100
    println(test2Class.counter)
    println(test2Class.isEmpty)

    println(test2Class.table::class.java)
}

// 声明属性
// Kotlin的类可以有属性。 属性可以用关键字var 声明为可变的，否则使用只读关键字val。
class Address {
    var name: String = "Tom"
    var street: String = "11"
    var city: String = "11"
    var state: String? = null
    var zip: String = "11"
}

// 要使用一个属性，只要用名称引用它即可，就像 Java 中的字段：
fun copyAddress(address: Address): Address {
    val result = Address() // Kotlin 中没有“new”关键字
    result.name = address.name // 将调用访问器
    result.street = address.street
    result.city = address.city
    result.state = address.state
    result.zip = address.zip
    return result
}

// Getters 和 Setters
// 声明一个属性的完整语法是
/*var <propertyName>[: <PropertyType>] [= <property_initializer>]
[<getter>]
[<setter>]
其初始器（initializer）、getter 和 setter 都是可选的。
属性类型如果可以从初始器 （或者从其 getter 返回值，如下文所示）中推断出来，也可以省略。*/
// 例如:
class TestClass {
    private var size: Int = 0
    private var other: String? = null

    //var allByDefault: Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
    var initialized = 1 // 类型 Int、默认 getter 和 setter
    // 一个只读属性的语法和一个可变的属性的语法有两方面的不同：
    // 1、只读属性的用 val开始代替var 2、只读属性不允许 setter

    //val simple: Int? // 类型 Int、默认 getter、必须在构造函数中初始化
    val inferredType = 1 // 类型 Int 、默认 getter

    // 我们可以编写自定义的访问器，非常像普通函数，刚好在属性声明内部。
    // 这里有一个自定义 getter 的例子:
    val isEmpty: Boolean
        get() = this.size == 0

    // 一个自定义的 setter 的例子:
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            setDataFromString(value) // 解析字符串并赋值给其他属性
        }

    private fun setDataFromString(value: String) {
        other = value
    }
    // 按照惯例，setter 参数的名称是 value，但是如果你喜欢你可以选择一个不同的名称。
    // 自 Kotlin 1.1 起，如果可以从 getter 推断出属性类型，则可以省略它：

    val isEmpty2 get() = this.size == 0  // 具有类型 Boolean
    // 如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现，
    // 你可以定义访问器而不定义其实现:

    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现

    var setterWithAnnotation: Any? = null
        @Inject set // 用 Inject 注解此 setter
}

// 幕后字段
// Kotlin 中类不能有字段。
// 然而，当使用自定义访问器时，有时有一个幕后字段（backing field）有时是必要的。
// 为此 Kotlin 提供一个自动幕后字段，它可通过使用 field 标识符访问。
class Test2Class {
    var counter = 0 // 此初始器值直接写入到幕后字段
        set(value) {
            if (value >= 0)
                field = value
        }
        get() {
            return field + 1
        }
    // field 标识符只能用在属性的访问器内。
    // 如果属性至少一个访问器使用默认实现，或者自定义访问器通过 field 引用幕后字段，将会为该属性生成一个幕后字段。
    // 例如，下面的情况下， 就没有幕后字段：
    val isEmpty: Boolean
        get() = this.counter == 0

    // 幕后属性
    // 如果你的需求不符合这套“隐式的幕后字段”方案，那么总可以使用 幕后属性（backing property）：

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
    // 从各方面看，这正是与 Java 相同的方式。
    // 因为通过默认 getter 和 setter 访问私有属性会被优化，所以不会引入函数调用开销。
}

// 编译期常量
// 已知值的属性可以使用 const 修饰符标记为 编译期常量。 这些属性需要满足以下要求：
/*位于顶层或者是 object 的一个成员
用 String 或原生类型 值初始化
没有自定义 getter
这些属性可以用在注解中：*/
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

class TestSS {
    @Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {
        //
    }
}

// 延迟初始化属性
// 一般地，属性声明为非空类型必须在构造函数中初始化。
// 然而，这经常不方便。例如：属性可以通过依赖注入来初始化， 或者在单元测试的 setup 方法中初始化。
// 这种情况下，你不能在构造函数内提供一个非空初始器。 但你仍然想在类体中引用该属性时避免空检查。
// 为处理这种情况，你可以用 lateinit 修饰符标记该属性：
/*
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // 直接解引用
    }
}*/
/*该修饰符只能用于在类体中（不是在主构造函数中）声明的 var 属性，
并且仅当该属性没有自定义 getter 或 setter 时。该属性必须是非空类型，并且不能是原生类型。
在初始化前访问一个 lateinit 属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。*/
