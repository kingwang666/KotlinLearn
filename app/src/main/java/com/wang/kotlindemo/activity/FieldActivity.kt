package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wang.kotlindemo.R
import com.wang.kotlindemo.function.getLogger
import com.wang.kotlindemo.mdoel.TextMode
import com.wang.kotlindemo.util.StringUtil

/**
 * Created on 2017/5/31.
 * Author: wang
 */
const val NAME = "FieldActivity"

class FieldActivity : AppCompatActivity() {

    val logger = getLogger()

    var stringRepresentation: String = "123"
        set(value) {
            logger(value) // 解析字符串并赋值给其他属性
            field = value
        }

    //    如果属性至少一个访问器使用默认实现，或者自定义访问器通过 field 引用幕后字段，将会为该属性生成一个幕后字段。
//    所以没有幕后字段
    val isActivity get() = javaClass.simpleName?.endsWith("Activity") ?: false

    /**
     *如果你的需求不符合这套“隐式的幕后字段”方案，那么总可以使用 幕后属性（backing property）：
     */
    private var _table: Map<String, Int>? = null
    val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    lateinit var model: TextMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        findViewById<View>(R.id.button1).setOnClickListener {
            stringRepresentation = "test"
            logger(stringRepresentation)
            logger(isActivity)
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            logger(NAME)
            logger(com.wang.kotlindemo.function.NAME)
            /**
             * StringUtil 并没有初始化
             */
            logger(StringUtil.NAME)
            StringUtil.TEST = "123"
            logger(StringUtil.TEST)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            model = TextMode(FieldActivity::class.java, "lateinit")
            logger(model)
        }
    }
}
