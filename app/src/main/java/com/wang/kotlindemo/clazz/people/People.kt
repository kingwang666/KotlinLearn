package com.wang.kotlindemo.clazz.people

/**
 * Created on 2017/5/31.
 * Author: wang
 */
abstract class People {

    protected var name: String
    private var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    open fun isAdult(): Boolean {
        return age >= 18
    }

    abstract fun isWomen(): Boolean

    internal constructor(name: String) : this(name, 18)

    protected constructor(age: Int) : this("empty", age)

    protected constructor() : this("empty", 18)

    override fun toString(): String {
        return "the $name's age is $age"
    }
}