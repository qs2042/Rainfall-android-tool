package com.qing.rainfall_tool

import com.qing.lib_common.NetworkUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        for (port in 78..65535) {
//            println("$port : ${Network.isOpen("192.168.0.101", port)}")
            println("$port : ${NetworkUtils.isOpen("39.156.66.10", port)}")
        }
    }
}