package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/13 0013.
 * 枚举类
 */
// 枚举类的最基本的用法是实现类型安全的枚举
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}
// 每个枚举常量都是一个对象。枚举常量用逗号分隔。

// 初始化
// 因为每一个枚举都是枚举类的实例，所以他们可以是初始化过的。
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 匿名类
// 枚举常量也可以声明自己的匿名类
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}
// 及相应的方法、以及覆盖基类的方法。
// 注意，如果枚举类定义任何成员，要使用分号将成员定义中的枚举常量定义分隔开，就像在 Java 中一样。

// 使用枚举常量
// 就像在 Java 中一样，Kotlin 中的枚举类也有合成方法允许列出定义的枚举常量以及通过名称获取枚举常量。
// 这些方法的签名如下（假设枚举类的名称是 EnumClass）：

// EnumClass.valueOf(value: String): EnumClass
// EnumClass.values(): Array<EnumClass>

// 如果指定的名称与类中定义的任何枚举常量均不匹配，
// valueOf() 方法将抛出 IllegalArgumentException 异常。

// 自 Kotlin 1.1 起，可
// 以使用 enumValues<T>() 和 enumValueOf<T>() 函数以泛型的方式访问枚举类中的常量 ：
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    println(enumValues<T>().joinToString(separator = "|") { it.name })
}

// 每个枚举常量都具有在枚举类声明中获取其名称和位置的属性：
// val name: String
// val ordinal: Int
// 枚举常量还实现了 Comparable 接口， 其中自然顺序是它们在枚举类中定义的顺序。

fun main(args: Array<String>) {
    println(ProtocolState.TALKING)
    println(ProtocolState.TALKING.signal())
    println(ProtocolState.valueOf("TALKING"))
    // Exception in thread "main" java.lang.IllegalArgumentException: No enum constant com.hxh.kt_syntax.ProtocolState.TALKING1
    // println(ProtocolState.valueOf("TALKING1"))

    printAllValues<RGB>()
}