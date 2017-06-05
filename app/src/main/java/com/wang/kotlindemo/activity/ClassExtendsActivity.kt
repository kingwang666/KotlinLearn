package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wang.kotlindemo.R
import com.wang.kotlindemo.clazz.KWEmpty
import com.wang.kotlindemo.clazz.people.People
import com.wang.kotlindemo.clazz.people.Wife
import com.wang.kotlindemo.clazz.people.Women
import com.wang.kotlindemo.function.getLogger
import com.wang.kotlindemo.function.log
import com.wang.kotlindemo.interfaces.OnRecyclerClickListener

/**
 * Created on 2017/5/31.
 * Author: wang
 */
class ClassExtendsActivity : AppCompatActivity() {

    val logger = getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_extends)

        findViewById(R.id.button1).setOnClickListener {
            val empty = KWEmpty()
            empty.log()
            empty.x = 3
            empty.log()
        }

        findViewById(R.id.button2).setOnClickListener {
            logger("${object : People("wang", 25) {
                override fun isWomen(): Boolean {
                    return false
                }
            }}")
            logger("${object : People("wang"), OnRecyclerClickListener {

                override fun onClick(itemType: Int, position: Int) {

                }

                override fun isWomen(): Boolean {
                    return false
                }
            }}")
//            logger("${People(25)}")
            val women = Women()
            women.isAdult()
            logger("$women")

            logger("${Wife()}")
        }

    }
}