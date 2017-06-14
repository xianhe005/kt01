package com.hxh.kt01

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */
fun main(args: Array<String>) {
    val user = User(0, "HXH")
    println(user)

    HelloKotlin::class.constructors.map(::println)

    HelloKotlin::class.constructors.map {
        println(it)
        println("test")
    }

    println(HelloKotlin::class.qualifiedName)
}

class HelloKotlin {
    fun hello(){

    }
}