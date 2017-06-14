package com.hxh.kt01

/**
 * a_b_c d_e f_g_h_j
 * a b c d e f g h j
 * Created by HXH on 2017/5/31 0031.
 */
fun main(vararg args: String) {
    args.flatMap {
        it.split("_")
    }.map {
        print("$it ")
        //print("$it ${it.length}")
    }
}