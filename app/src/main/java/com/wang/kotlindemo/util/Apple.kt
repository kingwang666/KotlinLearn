package com.wang.kotlindemo.util

import android.util.Log

/**
 * Created on 2017/5/31.
 * Author: wang
 */
class Apple {

    var mStep = 1

    fun wash(){
        Log.d("test", "the step $mStep is wash")
        mStep++
    }

    fun peel() {
        Log.d("test", "the step $mStep is peel")
        mStep++
    }

    fun eat() {
        Log.d("test", "the step $mStep is eat")
        mStep++
    }
}