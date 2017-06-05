package com.wang.kotlindemo.clazz

import com.wang.kotlindemo.function.getLogger
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created on 2017/6/2.
 * Author: wang
 */
class Delegate : ReadWriteProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        getLogger().invoke("$value has been assigned to '${property.name}' in $thisRef.")
    }
}