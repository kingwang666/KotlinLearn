package com.wang.kotlindemo.clazz

import com.wang.kotlindemo.function.getLogger

/**
 * Created on 2017/6/2.
 * Author: wang
 */
/**
 * 类似java类中的静态方法 请注意，即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们 仍然是真实对象的实例成员
 */
class CompanionObject {

    init {
        getLogger().invoke(this)
    }

    companion object {

        var aa = 1
        init {
            getLogger().invoke(this)
        }

        fun getInstance(): CompanionObject {
            getLogger().invoke("getInstance")
            return CompanionObject()
        }

        @JvmStatic
        fun getInstance2(): CompanionObject {
            getLogger().invoke("getInstance2")
            return CompanionObject()
        }
    }
}