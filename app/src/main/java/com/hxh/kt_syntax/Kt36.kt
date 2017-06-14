package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/12 0012.
 * 数据类
 */
// 我们经常创建一些只保存数据的类。在这些类中，一些标准函数往往是从数据机械推导而来的。
// 在 Kotlin 中，这叫做 数据类 并标记为 data：

data class User(val name: String, val age: Int)
// 编译器自动从主构造函数中声明的所有属性导出以下成员：

// equals()/hashCode() 对，
// toString() 格式是 "User(name=John, age=42)"，
// componentN() 函数 按声明顺序对应于所有属性，
// copy() 函数（见下文）。
// 如果这些函数中的任何一个在类体中显式定义或继承自其基类型，则不会生成该函数。

// 为了确保生成的代码的一致性和有意义的行为，数据类必须满足以下要求：

// 主构造函数需要至少有一个参数；
// 主构造函数的所有参数需要标记为 val 或 var；
// 数据类不能是抽象、开放、密封或者内部的；
// （在1.1之前）数据类只能实现接口。
// 自 1.1 起，数据类可以扩展其他类（示例请参见密封类）。

// 在 JVM 中，如果生成的类需要含有一个无参的构造函数，则所有的属性必须指定默认值。 （参见构造函数）。
data class User2(val name: String = "", val age: Int = 0)

// 复制
// 在很多情况下，我们需要复制一个对象改变它的一些属性，但其余部分保持不变。
// copy() 函数就是为此而生成。对于上文的 User 类，其实现会类似下面这样：
// fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

data class User3(val name: String = "", val age: Int = 0) {
}

fun main(args: Array<String>) {
    val jack = User3(name = "Jack", age = 1)
    println(jack)
    val olderJack = jack.copy(age = 2)
    println(olderJack)

    // 数据类和解构声明
    // 为数据类生成的 Component 函数 使它们可在解构声明中使用：
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // 输出 "Jane, 35 years of age"
}
// 标准数据类
// 标准库提供了 Pair 和 Triple。
// 尽管在很多情况下命名数据类是更好的设计选择， 因为它们通过为属性提供有意义的名称使代码更具可读性。