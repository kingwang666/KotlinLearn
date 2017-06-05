package com.wang.kotlindemo.clazz.people

import android.util.Log

/**
 * Created on 2017/5/31.
 * Author: wang
 */
open class Women : People(20), PeopleLog {


    open var married: Boolean = false

    final override fun isAdult(): Boolean {
        var adult = super<People>.isAdult()
        Log.d("test", "the people is adult $adult")
        adult = super<PeopleLog>.isAdult()
        Log.d("test", "the peopleLog is adult $adult")
        return true
    }

    override fun isWomen(): Boolean {
        return true
    }

    override fun toString(): String {
        return "the women name is $name, and she ${if (married) "has" else "no"} married"
    }
}