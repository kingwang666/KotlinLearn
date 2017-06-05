package com.wang.kotlindemo.clazz

import com.wang.kotlindemo.function.getLogger

/**
 * Created on 2017/6/2.
 * Author: wang
 */
/**
 * 扩展接受者
 */
class Chicken {

    fun eat(){
        getLogger().invoke("eat chicken")
    }
}

/**
 * 分发接受者
 */
open class Duck{

    fun drink(){
        getLogger().invoke("drink duck blood")
    }

    open fun Chicken.foo(){
        eat()
        drink()
        getLogger().invoke(toString())
        getLogger().invoke(this@Duck.toString())
    }

    fun caller(chicken: Chicken) {
        chicken.foo()   // 调用扩展函数
    }
}

class BlackDuck : Duck(){

    override fun Chicken.foo() {
        getLogger().invoke("it is black duck foo")
    }
}