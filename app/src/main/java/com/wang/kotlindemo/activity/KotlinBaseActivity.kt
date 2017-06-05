package com.wang.kotlindemo.activity

import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wang.kotlindemo.R
import com.wang.kotlindemo.function.getLogger
import com.wang.kotlindemo.function.sum
import com.wang.kotlindemo.function.toFuck
import com.wang.kotlindemo.util.Apple
import com.wang.kotlindemo.util.StringUtil

/**
 * Created on 2017/5/27.
 * Author: wang
 */
class KotlinBaseActivity : AppCompatActivity() {
    
    val logger = getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base)


        findViewById(R.id.button1).setOnClickListener {
            var sum = sum(1, 2)
            logger("sum = $sum")
            sum = sum(1, 2, 3)
            logger("sum = $sum")
        }
        findViewById(R.id.button2).setOnClickListener {
            sum1(1, 2)
        }
        findViewById(R.id.button3).setOnClickListener {
            logger("${maxOf(3, 1)}")
            logger("${maxOf1(1, 2)}")
        }
        findViewById(R.id.button4).setOnClickListener {
            logger("${maxIsA(3, 1)}")
            val max = maxIsA(1, 3)
            logger("$max")
        }

        findViewById(R.id.button5).setOnClickListener {
            logger("${judgeString("123")}")
            logger("${judgeString(123)}")
        }

        findViewById(R.id.button6).setOnClickListener {
            val names = listOf("1", "2", "3")
            for (name in names) {
                logger("for $name")
            }
            for (position in names.indices) {
                logger("for $position is ${names[position]}")
            }
            var position = 0
            while (position < names.size) {
                logger("while $position is ${names[position]}")
                position++
            }

            for (index in 0 until 10 step 2) {
                logger("for until $index")
            }

            for (index in 10 downTo 0 step 2) {
                logger("for $index")
            }
        }

        findViewById(R.id.button7).setOnClickListener {
            val counts = defaultValue()
            counts.map { value ->
                logger("value $value")
                "$value"
            }
                    .filter { text -> text != "2" }
                    .forEach { value -> logger("text value $value") }

            counts.map {
                logger("it $it")
                "$it"
            }
                    .filter { it != "2" }
                    .forEach { value -> logger("text it $value") }

        }

        findViewById(R.id.button8).setOnClickListener {
            val map = mapOf("姓" to "王", "名" to "晓杰")
            for ((key, value) in map) {
                logger("$key is $value")
            }
        }

        findViewById(R.id.button9).setOnClickListener {
            var map = mapOf("姓" to "王", "名" to "晓杰")
            var list = listOf("1", "2", "3")
//            map["名"] = "先生"
//            list.add(0, "0")

            map = map.toMutableMap()
            list = list.toMutableList()
            map["名"] = "先生"
            list.add(0, "0")
            for ((key, value) in map) {
                logger("$key is $value")
            }
            for (text in list) {
                logger(text)
            }
        }

        findViewById(R.id.button10).setOnClickListener {
            var str = "124"
            str = str.toFuck()
            logger(str)
        }

        findViewById(R.id.button11).setOnClickListener {
            var size = StringUtil.getStringSize(null)
            logger("size $size")
            size = StringUtil.getStringSize("123")
            logger("size $size")
        }

        findViewById(R.id.button12).setOnClickListener {
            val apple = Apple()
            with(apple) {
                wash()
                peel()
                eat()
            }

            val a = decimalDigitValue('2')
            if (a is Int) {
                logger("the a is $a")
            }
        }

        findViewById(R.id.button13).setOnClickListener {
            testReturn()
        }

    }


    fun sum1(a: Int, b: Int): Unit {
        logger("a + b = ${a + b}")
    }

    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun maxOf1(a: Int, b: Int) = if (a > b) a else {
        logger("max is b")
        b
    }

    fun maxIsA(a: Int, b: Int): Int? {
        if (a > b) {
            return a
        }
        return null
    }

    fun judgeString(any: Any): Any {
        if (any is String) {
            return any.length
        }
        return "The any is not String"
    }

    fun defaultValue(size: Int = 6): List<Int> {
        return List(size) { index -> size - index }
    }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    fun testReturn() {
        val counts = defaultValue(8)


        counts.map {
            logger("map 1 $it")
            return@map it
        }
                .forEach { logger("for each 1 $it") }

        counts.map {
            logger("map 2 $it")
            return
        }
                .forEach { logger("for each 2 $it") }
    }

}