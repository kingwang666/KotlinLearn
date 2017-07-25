package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wang.kotlindemo.R
import com.wang.kotlindemo.clazz.CompanionObject

/**
 * Created on 2017/6/2.
 * Author: wang
 */
class ObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)
        findViewById<View>(R.id.button1).setOnClickListener {
            CompanionObject.getInstance()
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            CompanionObject.getInstance2()
        }
    }


    class C {
        // 私有函数，所以其返回类型是匿名对象类型
        private fun foo() = object {
            val x: String = "x"
        }

        // 公有函数，所以其返回类型是 Any
        fun publicFoo() = object {
            val x: String = "x"
        }

        fun bar() {
            val x1 = foo().x        // 没问题
//            val x2 = publicFoo().x  // 错误：未能解析的引用“x”
        }
    }
}