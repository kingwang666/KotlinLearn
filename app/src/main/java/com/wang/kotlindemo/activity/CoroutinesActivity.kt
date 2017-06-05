package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wang.kotlindemo.R
import com.wang.kotlindemo.function.getLogger
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildSequence

/**
 * Created on 2017/6/5.
 * Author: wang
 */
class CoroutinesActivity : AppCompatActivity() {

    val logger = getLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        findViewById(R.id.button1).setOnClickListener {
            launch(CommonPool) {
                logger(Thread.currentThread().name)
            }
            async(CommonPool) {
                logger(Thread.currentThread().name)
            }

        }

        findViewById(R.id.button2).setOnClickListener {
            val lazySeq = buildSequence {
                logger("START ")
                for (i in 1..5) {
                    yield(i) //到这结束
                    logger("STEP ")
                }
                logger("END")
            }
            lazySeq.take(3).forEach { logger(it) }
        }

        findViewById(R.id.button3).setOnClickListener {
            val lazySeq = buildSequence {
                logger("START ")
                for (i in 1..5) {
                    yield(i) //到这结束
                    logger("STEP ")
                }
                logger("END")
                yieldAll(6..10)
            }
            lazySeq.take(3).forEach { logger(it) }
            lazySeq.take(7).forEach { logger(it) }
        }

        findViewById(R.id.button4).setOnClickListener {
            val lazySeq = buildSequence {
                addIfEven(2,3,5)
                addIfEven(2,8)
            }
            lazySeq.forEach { logger(it) }
        }
    }

    suspend fun SequenceBuilder<Int>.addIfEven(vararg ints: Int) {
        val sum = ints
                .filter { it % 2 == 0 }
                .sum()
        yield(sum)
    }

//    suspend fun delay(index: Int): Int {
//        logger(Thread.currentThread().name)
//        return index
//    }
}