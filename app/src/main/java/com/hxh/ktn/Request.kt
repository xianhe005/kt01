package com.hxh.ktn

import java.net.URL

/**
 * Created by HXH on 2017/6/2 0002.
 *
 */
class Request(var url: String){

    fun run(){
        val forecastJsonStr = URL(url).readText()
        println(forecastJsonStr)
    }
}

fun main(args: Array<String>) {
    Request("http://www.baidu.com").run()
}