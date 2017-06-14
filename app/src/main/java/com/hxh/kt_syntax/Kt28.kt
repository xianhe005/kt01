package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/5 0005.
 *
 */
// 数字字面值中的下划线（自 1.1 起）
// 你可以使用下划线使数字常量更易读：
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

fun main(args: Array<String>) {
    println(oneMillion)
    println(creditCardNumber)
    println(socialSecurityNumber)
    println(hexBytes)
    println(bytes)

    println(oneMillion::class.java)
    println(creditCardNumber::class.java)
    println(socialSecurityNumber::class.java)
    println(hexBytes::class.java)
    println(bytes::class.java)

    boxed()

    boxed2()

    cast()
}

private fun cast() {
    // 显式转换
    // 由于不同的表示方式，较小类型并不是较大类型的子类型。 如果它们是的话，就会出现下述问题：
    // 假想的代码，实际上并不能编译：
    //val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
    //val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
    //print(a == b) // 惊！这将打印 "false" 鉴于 Long 的 equals() 检测其他部分也是 Long

    // 所以同一性还有相等性都会在所有地方悄无声息地失去。
    // 因此较小的类型不能隐式转换为较大的类型。 这意味着在不进行显式转换的情况下我们不能把 Byte 型值赋给一个 Int 变量。
    val b: Byte = 1 // OK, 字面值是静态检测的
    //val i: Int = b // 错误
    //我们可以显式转换来拓宽数字
    val i: Int = b.toInt() // OK: 显式拓宽

    /*每个数字类型支持如下的转换:
    toByte(): Byte
    toShort(): Short
    toInt(): Int
    toLong(): Long
    toFloat(): Float
    toDouble(): Double
    toChar(): Char*/

    // 缺乏隐式类型转换并不显著，因为类型会从上下文推断出来，而算术运算会有重载做适当转换，例如：
    val l = 1L + 3 // Long + Int => Long


    // 运算
    // Kotlin支持数字运算的标准集，运算被定义为相应的类成员（但编译器会将函数调用优化为相应的指令）。
    // 对于位运算，没有特殊字符来表示，而只可用中缀方式调用命名函数，例如:
    val x = (1 shl 2) and 0x000FF000
    println(1 shl 2)
    println(0x000FF000)

    /*这是完整的位运算列表（只用于 Int 和 Long）：
    shl(bits) – 有符号左移 (Java 的 <<)
    shr(bits) – 有符号右移 (Java 的 >>)
    ushr(bits) – 无符号右移 (Java 的 >>>)
    and(bits) – 位与
    or(bits) – 位或
    xor(bits) – 位异或
    inv() – 位非*/

    // 字符
    // 字符用 Char 类型表示。它们不能直接当作数字
    fun check(c: Char) {
        //if (c == 1) { // 错误：类型不兼容
        // ……
        //}
    }

    // 字符字面值用单引号括起来: '1'。 特殊字符可以用反斜杠转义。
    // 支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 和 \$。
    // 编码其他字符要用 Unicode 转义序列语法：'\uFF00'。

    // 我们可以显式把字符转换为 Int 数字：
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    /*布尔
    布尔用 Boolean 类型表示，它有两个值：true 和 false。
    若需要可空引用布尔会被装箱。
    内置的布尔运算有：
    || – 短路逻辑或
    && – 短路逻辑与
    ! - 逻辑非*/

    // 数组
    // 数组在 Kotlin 中使用 Array 类来表示，它定义了 get 和 set 函数（按照运算符重载约定这会转变为 []）和 size 属性，
    // 以及一些其他有用的成员函数：

    // 我们可以使用库函数 arrayOf() 来创建一个数组并传递元素值给它，这样 arrayOf(1, 2, 3) 创建了 array [1, 2, 3]。
    val arrayOf = arrayOf(1, 2, 3)
    // 或者，库函数 arrayOfNulls() 可以用于创建一个指定大小、元素都为空的数组。
    val arrayOfNulls = arrayOfNulls<String>(5)
    // 另一个选项是用接受数组大小和一个函数参数的工厂函数，用作参数的函数能够返回 给定索引的每个元素初始值：
    // 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val array = Array(5, { (it * it).toString() })
    array.forEach { println(it) }

    // 注意: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。
    // 这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>，以防止可能的运行时失败（但是你可以使用 Array<out Any>, 参见类型投影）。

    // Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 ShortArray、IntArray 等等。
    // 这些类和 Array 并没有继承关系，但是 它们有同样的方法属性集。它们也都有相应的工厂方法:
    val xx: IntArray = intArrayOf(1, 2, 3)
    xx.forEach { println(it) }
    xx[0] = xx[1] + xx[2]
    xx.forEach { println(it) }

    // 字符串
    // 字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]。 可以用 for 循环迭代字符串:
    val str = "hello"
    for (c in str) {
        println(c)
    }
    str.forEach { println(it) }

    // 字符串字面值
    // Kotlin 有两种类型的字符串字面值: 转义字符串可以有转义字符，以及原生字符串可以包含换行和任意文本。转义字符串很像 Java 字符串:
    val s = "Hello, world!\n"

    // 转义采用传统的反斜杠方式。参见上面的 字符 查看支持的转义序列。
    // 原生字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行和任何其他字符:
    val text = """
    for (c in "foo")
        print(c)
    """
    for (c in text) {
        println(c)
    }
    println("===================")

    // 你可以通过 trimMargin() 函数去除前导空格：
    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    for (c in text2) {
        println(c)
    }
    // 默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。

    // 字符串模板
    // 字符串可以包含模板表达式 ，即一些小段代码，会求值并把结果合并到字符串中。 模板表达式以美元符（$）开头，由一个简单的名字构成:

    val i1 = 10
    val s1 = "i1 = $i" // 求值结果为 "i1 = 10"
    // 或者用花括号扩起来的任意表达式:
    val s2 = "abc"
    val str2 = "$s2.length is ${s2.length}" // 求值结果为 "abc.length is 3"
    // 原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：
    val price = """${'$'}9.99"""
    println("price is $price")
}

private fun boxed2() {
    //另一方面，它保留了相等性:
    val a: Int = 10000
    println(a == a) // 输出“true”
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA == anotherBoxedA) // 输出“true”
}

private fun boxed() {
    // 表示方式
    // 在 Java 平台数字是物理存储为 JVM 的原生类型，除非我们需要一个可空的引用（如 Int?）或泛型。 后者情况下会把数字装箱。
    // 注意数字装箱不必保留同一性:
    val a: Int = 10000
    println(a === a) // 输出“true”
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // ！！！输出“false”！！！
}