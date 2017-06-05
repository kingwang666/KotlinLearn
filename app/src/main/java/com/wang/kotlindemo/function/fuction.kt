package com.wang.kotlindemo.function

import android.util.Log
import com.wang.kotlindemo.clazz.CompanionObject
import com.wang.kotlindemo.clazz.KWEmpty

/**
 * Created on 2017/5/27.
 * Author: wang
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int, c: Int) = a + b + c

fun String.toFuck(): String {
    return "fuck"
}

fun KWEmpty.log() {
    getLogger().invoke("${this.javaClass.simpleName} x is ${this.x}")
}

fun getLogger(): (Any?) -> Unit {
    return {
        Log.d("test", "$it")
    }
//        return fun (it: String){
//            Log.d("test", it)
//        }
}

const val NAME = "name"

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun CompanionObject.Companion.log() {
    getLogger().invoke("伴生对象扩展")
}

infix fun Int.center(y: Int): Float {
    return (this + y) / 2.0f
}