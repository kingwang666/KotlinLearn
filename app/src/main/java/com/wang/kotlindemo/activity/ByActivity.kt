package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.wang.kotlindemo.R
import com.wang.kotlindemo.clazz.Delegate
import com.wang.kotlindemo.clazz.bindView
import com.wang.kotlindemo.function.getLogger
import kotlin.properties.Delegates

/**
 * Created on 2017/6/2.
 * Author: wang
 */
class ByActivity : AppCompatActivity() {

    val logger = getLogger()

    var str: String by Delegate()

    val btn: Button by bindView(R.id.button6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_by)

        findViewById<View>(R.id.button1).setOnClickListener {
            Boss(Staff("king wang")).working()
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            logger(str)
            str = "test"
            logger(str)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            val lazy: Int by lazy {
                logger("lazy start")
                3
            }

            logger(lazy)
            logger(lazy)
        }

        findViewById<View>(R.id.button4).setOnClickListener {
            var score: Int by Delegates.observable(0) {
                property, oldValue, newValue ->
                logger("the ${property.name} is $oldValue -> $newValue")

            }

            score = 60
            score = 100
        }

        findViewById<View>(R.id.button5).setOnClickListener {
            var map = mapOf("name" to "wang", "age" to 18)
            val name: String by map
            val age:Int by map
            logger("the $name's age is $age")
            map = mutableMapOf("name1" to "xiao", "age1" to 25)
            var name1: String by map
            var age1:Int by map
            logger("the $name1's age is $age1")
        }

        findViewById<View>(R.id.button6).setOnClickListener {
            logger(btn)
        }
    }

    interface Work {
        fun working()
    }

    class Staff(val name: String) : Work {
        override fun working() {
            getLogger().invoke("the Staff $name is working")
        }
    }

    class Boss(staff: Staff) : Work by staff

}