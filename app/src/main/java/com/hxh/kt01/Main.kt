package com.hxh.kt01

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */
fun main(args: Array<String>) {
    println("Hello World!")
    println(Main("HXH", 0))
    println(Main2(0, "HXH"))
}

class Main(val name: String, val id: Int) {

    override fun toString(): String {
        return "$id - $name"
    }
}

data class Main2(val id: Int, val name: String)

data class User(val id : Int, val name : String)