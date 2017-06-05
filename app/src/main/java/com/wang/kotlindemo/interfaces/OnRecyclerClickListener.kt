package com.wang.kotlindemo.interfaces

/**
 * Created on 2017/5/27.
 * Author: wang
 */
interface OnRecyclerClickListener {
    fun onClick(itemType: Int = 0, position: Int = 0)
}