package com.wang.kotlindemo.clazz

import android.graphics.Color
import android.util.Log

/**
 * Created on 2017/8/11.
 * Author: wang
 */

class ImageUtil {

    companion object {

        fun grey(pixels: IntArray): IntArray? {
            val length = pixels.size
            if (length == 0) {
                Log.e("Error", "the source pixel is length == 0")
                return null
            }
            val greyPixels = IntArray(length)
            for (i in 0..length - 1) {
                val pixel = pixels[i]
                val grey = rgbToGrey(pixel)
                val a = pixel.ushr(24)
                greyPixels[i] = a shl 24 or (grey shl 16) or (grey shl 8) or grey
            }
            return greyPixels
        }

        /**
         * 大津法 进行二值化
         * @param pixels 输入像素
         * *
         * @param greyPixels 输出的灰度像素
         * *
         * @param binaryPixels 输出的二值像素
         * *
         * @return 阈值
         */
        fun OTSU(pixels: IntArray, greyPixels: IntArray?, binaryPixels: IntArray): Int {

            if (pixels.isEmpty()) {
                Log.e("Error", "the source pixel is length == 0")
                return -1
            }
            /**
             * 是否获取灰度图
             */
            var getGreyPixel = false
            /**
             * 总的灰度值
             */
            var sumGrey = 0
            /**
             * 总的前景灰度值
             */
            var sumFrontGrey = 0
            /**
             * 阈值
             */
            var thresholdValue = 0
            /**
             * 总的像素点个数
             */
            val n = pixels.size
            /**
             * 前景(像素小于阈值)像素点个数
             */
            var n0 = 0
            /**
             * 背景(像素大于阈值)像素点个数
             */
            var n1: Int
            /**
             * 前景平均灰度值
             */
            var u0: Double
            /**
             * 背景平均灰度值
             */
            var u1: Double
            /**
             * 最大类间方差
             */
            var maxG = 0.0
            /**
             * 类间方差
             */
            var g: Double

            val greys = IntArray(n)
            val hist = IntArray(256)

            if (greyPixels != null) {
                if (greyPixels.size == n) {
                    getGreyPixel = true
                } else {
                    Log.e("Error", "the grey pixel length must = width * height")
                }
            }

            if (binaryPixels.size != n) {
                Log.e("Error", "the binary pixel length must = width * height")
                return -1
            }


            for (i in 0..n - 1) {
                val pixel = pixels[i]
                val grey = rgbToGrey(pixel)
                greys[i] = grey
                sumGrey += grey
                hist[grey]++
                if (getGreyPixel) {
                    greyPixels?.let {
                        val a = pixel.ushr(24)
                        greyPixels[i] = a shl 24 or (grey shl 16) or (grey shl 8) or grey
                    }
                }
            }

            for (i in 0..255) {
                n0 += hist[i]
                if (n0 == 0) {
                    continue
                }
                n1 = n - n0
                if (n1 == 0) {
                    break
                }
                sumFrontGrey += i * hist[i]
                u0 = sumFrontGrey.toDouble() / n0
                u1 = (sumGrey - sumFrontGrey).toDouble() / n1
                g = n0 * n1 * (u0 - u1) * (u0 - u1)
                // 类间方差最大的分割意味着错分概率最小
                if (g > maxG) {
                    maxG = g
                    thresholdValue = i
                }
            }

            for (i in 0..n - 1) {
                if (greys[i] >= thresholdValue) {
                    binaryPixels[i] = Color.WHITE
                } else {
                    binaryPixels[i] = Color.BLACK
                }
            }
            return thresholdValue
        }

        /**
         * 加权平均法 进行灰度化
         * @param pixel 像素点
         * *
         * @return 灰度值
         */
        private fun rgbToGrey(pixel: Int): Int {
            val r = pixel shr 16 and 0xFF
            val g = pixel shr 8 and 0xFF
            val b = pixel and 0xFF
            return (0.30 * r + 0.59 * g + 0.11 * b).toInt()
        }
    }
}
