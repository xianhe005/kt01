package com.hxh.kt01

import rx.Observable

/**
 * Created by HXH on 2017/5/31 0031.
 *
 */
fun main(args: Array<String>) {
    val text = "This reference is designed for you to easily learn Kotlin in a matter of hours. Start with the basic syntax, then proceed to more advanced topics. While reading, you can try out the examples in the online IDE. Once you get an idea of what Kotlin looks like, try yourself in solving some Kotlin Koans - interactive programming exercises. If you are not sure how to solve a Koan, or you're looking for a more elegant solution, check out Kotlin idioms."
    val asIterable = text.toCharArray().asIterable()
    println(asIterable)
    Observable.from(asIterable)
            .filter {
                //println(it)
                !it.isWhitespace()
            }
            .groupBy { it }
            .subscribe {
                o ->
                println(o)
                o.count().subscribe {
                    println("${o.key} -> $it")
                }
            }
}
