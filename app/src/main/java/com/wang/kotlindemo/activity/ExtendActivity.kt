package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wang.kotlindemo.R
import com.wang.kotlindemo.clazz.*
import com.wang.kotlindemo.function.getLogger
import com.wang.kotlindemo.function.log
import com.wang.kotlindemo.function.swap

/**
 * Created on 2017/6/2.
 * Author: wang
 * 扩展
 */
class ExtendActivity : AppCompatActivity() {

    val logger = getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extend)
        findViewById<View>(R.id.button1).setOnClickListener {
            val list: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6)
            list.forEach(logger)
            list.swap(0, 5)
            list.forEach { logger(it) }
        }

        /**
         * 扩展解析是静态的 并且优先成员函数
         */
        findViewById<View>(R.id.button2).setOnClickListener {
            val parent = Parent()
            val child = Child()
            with(parent) {
                eat()
                eat("money")
            }
            with(child) {
                eat()
                eat("the parent life")
            }
            logDrink1(child)
            logDrink2(child)
            logDrink3(child)
            logDrink3(parent)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            val list: MutableList<Int> = mutableListOf(1, 2)
            var index = (list.size - 1) / 2
            logger("the size is ${list.size}, the center index is $index")
            logger(list.center)
            list.add(3)
            index = (list.size - 1) / 2
            logger("the size is ${list.size}, the center index is $index")
            logger(list.center)
        }

        findViewById<View>(R.id.button4).setOnClickListener {
            CompanionObject.log()
        }

        findViewById<View>(R.id.button5).setOnClickListener {
            Duck().caller(Chicken())
            Duck().caller(OlderChicken())//扩展接收者静态解析
            BlackDuck().caller(Chicken()) //分发接收者虚拟解析
        }
    }

    open inner class Parent {

        fun eat() {
            logger("the parent eat rice")
        }
    }

    inner class Child : Parent()

    fun Child.eat() {
        logger("the child eat milk")
    }

    fun Child.eat(any: Any) {
        logger("the child eat $any")
    }

    fun Parent.eat() {
        logger("the  Parent eat meat, not eat rice")
    }

    fun Parent.eat(any: Any) {
        logger("the  Parent eat $any")
    }

    fun Child.drink() {
        logger("the  Child drink water")
    }

    fun Parent.drink() {
        logger("the Parent drink wine")
    }

    fun logDrink1(parent: Parent) {
        parent.drink()
    }

    fun logDrink2(child: Child) {
        child.drink()
    }

    fun <T : Parent> logDrink3(child: T) {
        when (child) {
            is Child -> {
                child.drink()
            }
            is Parent -> {
                child.drink()
            }
        }

    }

    /**
     * val Foo.bar = 1 // 错误：扩展属性不能有初始化器
     */
    val <T> List<T>.center get() = (this.size - 1) / 2
}