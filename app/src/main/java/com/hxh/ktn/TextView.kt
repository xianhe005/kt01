package com.hxh.ktn

/**
 * Created by HXH on 2017/6/2 0002.
 *
 */
class TextView(var text: String) {
    fun getText2() = text
    fun setText2(text: String) {
        this.text = text
    }
}
public var TextView.value: String
    get() = getText2() + " add"
    set(v) = setText2(v + " add")


fun main(args: Array<String>) {
    val textView = TextView("test")
    println(textView.text)
    println(textView.getText2())
    textView.value = "txt"
    println(textView.text)
    println(textView.getText2())
    println(textView.value)
}