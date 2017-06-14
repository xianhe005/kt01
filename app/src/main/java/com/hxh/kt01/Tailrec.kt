package com.hxh.kt01

import java.math.BigInteger

/**
 * Created by HXH on 2017/5/31 0031.
 * 尾递归
 */
class Result(var value: BigInteger = BigInteger.ONE)

tailrec fun factorial(num: Int, result: Result) {
    if (num == 1) result.value = result.value.times(BigInteger.ONE)
    else{
        result.value = result.value.times(BigInteger.valueOf(num.toLong()))
        factorial(num - 1, result)
    }
}

fun main(args: Array<String>) {
    val result = Result()
    factorial(10000, result)
    println(result.value)
}