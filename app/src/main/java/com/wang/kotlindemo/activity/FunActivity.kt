package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wang.kotlindemo.R
import com.wang.kotlindemo.clazz.people.People
import com.wang.kotlindemo.clazz.people.Women
import com.wang.kotlindemo.function.center
import com.wang.kotlindemo.function.getLogger

/**
 * Created on 2017/6/2.
 * Author: wang
 */
class FunActivity : AppCompatActivity() {

    val logger = getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun)

        findViewById(R.id.button1).setOnClickListener {
            logger("the 1 to 2 center is ${1 center 2}")
        }

        findViewById(R.id.button2).setOnClickListener {


            val a = arrayOf(1, -1)
            logger("the list is ${asList(1, 2, *a, 3)}")
        }

        findViewById(R.id.button3).setOnClickListener {
            logPeople(object : People() {
                override fun isWomen(): Boolean {
                    return false
                }
            })
        }

        findViewById(R.id.button4).setOnClickListener {
            var start = System.currentTimeMillis()
            var fix = findFixPoint(1.0)
            logger("the time is ${System.currentTimeMillis() - start}, the fix point is $fix")
            start = System.currentTimeMillis()
            fix = findFixPoint()
            logger("the time is ${System.currentTimeMillis() - start}, the fix point is $fix")
        }

        findViewById(R.id.button5).setOnClickListener {
            val list = listOf(1, 2, 3, 4, 5)
            //            val max = max(list) { max, a -> max < a }
//            val max = max(list, {max, a -> max < a})
            fun compare(max: Int, a: Int) = max < a
            logger(max(list, ::compare))
        }

        val sum = fun Int.(other: Int): Int = this + other


        findViewById(R.id.button6).setOnClickListener {
            var sum = IntSum { this + it }
            logger(sum)
            sum = 1.sum(2)
            logger(sum)
//            logger(membersOf<StringBuilder>().joinToString("\n"))
        }

        fun length(s: String) = s.length
        fun isOdd(x: Int) = x % 2 != 0

        findViewById(R.id.button7).setOnClickListener {
            val oddLength = compose(::isOdd, ::length)
            val strings = listOf("a", "ab", "abc")
            logger(strings.filter(oddLength))
        }


    }

    inline fun IntSum(sum: Int.(other: Int) -> Int): Int {
        return 1.sum(3)
    }


    fun <T> asList(vararg ts: T): List<T> {
        val list = ArrayList<T>()
        list.addAll(ts)
        list.forEach(fun(item) { logger(item) })
        return list
    }

    fun logPeople(people: People) {
        fun logSex(sex: String?) {
            if (sex == null) {
                logger("the people is men")
                logPeople(Women())
            } else {
                logger(sex)
            }
        }

        logSex(if (people.isWomen()) "women" else null)
    }

    tailrec fun findFixPoint(x: Double = 1.0): Double
            = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

    private fun findFixPoint(): Double {
        var x = 1.0
        while (true) {
            val y = Math.cos(x)
            if (x == y) return y
            x = y
        }
    }

    inline fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
        var max: T? = null
        for (it in collection)
            if (max == null || less(max, it))
                max = it
        return max
    }

    inline fun f(crossinline body: () -> Unit) {
        val f = Runnable { body() }
    }

    inline fun <reified T> membersOf() = T::class.members


    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

}