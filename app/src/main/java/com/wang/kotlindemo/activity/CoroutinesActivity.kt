package com.wang.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wang.kotlindemo.R
import com.wang.kotlindemo.function.getLogger
import kotlinx.coroutines.experimental.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
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

        findViewById<View>(R.id.button1).setOnClickListener {
            async(CommonPool) {
                delay(1000)
                logger("async ${Thread.currentThread().name}")
            }
            launch(CommonPool) {
                logger("launch ${Thread.currentThread().name}")
            }
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            val lazySeq = buildSequence {
                logger("START ")
                for (i in 1..5) {
                    yield(i) //到这结束
                    logger("STEP ")
                }
                logger("END")
            }
            lazySeq.take(3).forEach { logger(it) }
            logger("----------------------------------------------------")
            lazySeq.take(5).forEach { logger(it) }
            logger("----------------------------------------------------")
            lazySeq.take(6).forEach { logger(it) }
        }

        findViewById<View>(R.id.button3).setOnClickListener {
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
            logger("----------------------------------------------------")
            lazySeq.take(7).forEach { logger(it) }
        }

        findViewById<View>(R.id.button4).setOnClickListener {
            val lazySeq = buildSequence {
                addIfEven(2, 3, 5)
                addIfEven(2, 8)
            }
            lazySeq.forEach { logger(it) }
        }

        findViewById<View>(R.id.button5).setOnClickListener {

            thread {
                val time1 = System.currentTimeMillis()
                val sum1 = AtomicInteger()
                var countDownLatch = CountDownLatch(100000)
                logger("start")
                for (i in 1..100000) {
                    thread {
                        sum1.addAndGet(i)
                        countDownLatch.countDown()
                    }
                }
                countDownLatch.await()
                val time2 = System.currentTimeMillis()
                logger("result: ${sum1.get()}, time: ${time2 - time1}ms")
                val sum2 = AtomicInteger()
                countDownLatch = CountDownLatch(100000)
                for (i in 1..100000) {
                    launch(CommonPool) {
                        sum2.addAndGet(i)
                        countDownLatch.countDown()
                    }
                }
                countDownLatch.await()
                val time3 = System.currentTimeMillis()
                logger("result: ${sum2.get()}, time: ${time3 - time2}ms")
            }

        }

        findViewById<View>(R.id.button6).setOnClickListener {
            val addJob = async(CommonPool, CoroutineStart.LAZY) {
                logger("start")
                delay(3000)
            }
            launch(CommonPool) {
                logger("1")
                addJob.await()
                logger("end!")
            }
        }

        findViewById<View>(R.id.button7).setOnClickListener {
            val time1 = System.currentTimeMillis()
            logger("start")
            val deferred = (1..100000).map { n ->
                async(CommonPool) {
                    n
                }
            }
            launch(CommonPool) {
                val sum = deferred.sumBy { it.await() }
                val time2 = System.currentTimeMillis()
                logger("result: $sum, time: ${time2 - time1}ms")
            }
        }

        findViewById<View>(R.id.button8).setOnClickListener {
            thread {
                runBlocking {
                    val async1 = async(CommonPool) {
                        delay(1000)
                        logger("async1 over")
                    }

                    val async2 = async(CommonPool) {
                        delay(3000)
                        logger("async2 over")
                    }
                    asyncOver(async1.await(), async2.await())
                }
            }
        }

        findViewById<View>(R.id.button9).setOnClickListener {
            val deferred1: Deferred<Unit> = async(CommonPool) {
                logger("start 1")
                delay(1000)
                logger("end 1")
            }

            val deferred2: Deferred<Unit> = async(CommonPool) {
                try {
                    logger("start 2")
                }finally {
                    run(NonCancellable) {
                        delay(1000)
                        logger("end 2")
                    }
                }

            }
            deferred1.cancel()
            deferred2.cancel()
        }

        findViewById<View>(R.id.button10).setOnClickListener {
            async(CommonPool){
                try {
                    withTimeout(2000L){
                        logger("start")
                        delay(3000L)
                        logger("end")
                    }
                }catch (e: Exception){
                    logger("timeout")
                }

            }

        }
    }

    suspend fun SequenceBuilder<Int>.addIfEven(vararg ints: Int) {
        val sum = ints
                .filter { it % 2 == 0 }
                .sum()
        yield(sum)
    }

    fun asyncOver(await: Unit, await1: Unit) {
        logger("all over")
    }
}