package com.wang.kotlindemo.util

import android.util.Log

/**
 * Created on 2017/5/31.
 * Author: wang
 */
object StringUtil{

    const val NAME = "StringUtil"

    lateinit var TEST: String

    init {
        Log.d("test", "StringUtil init")
    }

    fun getStringSize(str: String?): Int{
        return str?.length ?: 0
    }
}
