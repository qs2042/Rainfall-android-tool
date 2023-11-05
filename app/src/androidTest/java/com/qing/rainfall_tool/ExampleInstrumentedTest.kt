package com.qing.rainfall_tool

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qing.lib_common.NetworkUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Log.i("2042136767", "useAppContext: ${appContext}")
        assertEquals("com.qing.rainfall_tool", appContext.packageName)

        val info = NetworkUtils.getIPInfo("39.156.66.10")

        println("res: $info")
    }
}