package com.hxh.kt_syntax

/**
 * Created by HXH on 2017/6/2 0002.
 * 遍历 map/pair型list
 */
fun main(args: Array<String>) {
    //只读 map
    var map = mapOf<String, Int>("a" to 1, "b" to 2, "c" to 3)
    //遍历 map/pair型list k、v 可以改成任意名字。
    for ((k, v) in map) {
        println("$k -> $v")
    }
    // 使用区间
    for (i in 1..10) {
        println(i)
    }  // 闭区间：包含 100
    for (i in 1 until 10) {
        println(i)
    } // 半开区间：不包含 100
    for (x in 2..10 step 2) {
        println(x)
    }
    for (x in 10 downTo 1) {
        println(x)
    }
    val x = 1
    if (x in 1..10) {
        println(x)
    }
    if (x !in 1..10) {
        println(x)
    }

    // 只读 list
    val list = listOf("a", "b", "c")

    // 访问 map
    println(map["b"])

    var te: String? = "ssss"
    // 延迟属性
    val p: String by lazy {
        te + "111"
    }
    println(te)
    //println(p)
    te = "res"
    println(p)

    "Convert this to camelcase".spaceToCamelCase()

    println(Resource.name)

    var list2: List<String>? = listOf("a", "b", "c")
    // If not null 缩写
    println(list2?.size)
    // If not null and else 缩写
    println(list2?.size ?: "empty")
    var list3: List<String>? = listOf()
    println(list3?.size)
    println(list3?.size ?: "empty")
    var list4: List<String>? = null
    println(list4?.size)
    println(list4?.size ?: "empty")


    // if null 执行一个语句
    //val email = map["email"] ?: throw IllegalStateException("Email is missing!")

    // if not null 执行代码
    map["a"]?.let {
        // 代码会执行到此处, 假如data不为null
        println("代码会执行到此处, 假如data不为null")
    }

    // “try/catch”表达式
    try {
        println(transform("Green"))
    } catch(e: Exception) {
        throw IllegalStateException(e)
    }

    foo(1)
    foo(2)
    foo(4)

    val arr = arrayOfMinusOnes(5)
    for (i in arr) {
        println(i)
    }

    // 使用可空布尔
    val b: Boolean? = null
    if (b == true) {
        println("`b` 是 true")
    } else{
        println("`b` 是 false 或者 null")
        // `b` 是 false 或者 null
    }
}

// 扩展函数
fun String.spaceToCamelCase() {
    println(toUpperCase())
}

// 创建单例
object Resource {
    val name = "Name"
}

// 返回when表达式
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun foo(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
    println(result)
}

// 返回类型为 Unit 的方法的 Builder 风格用法
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

// 单表达式函数
fun theAnswer() = 42

//等价于
fun theAnswer2(): Int {
    return 42
}

// 单表达式函数与其它惯用法一起使用能简化代码，例如和 when 表达式一起使用：
fun transform2(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}