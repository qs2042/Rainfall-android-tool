package com.qing.rainfall_tool

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import androidx.core.app.ActivityCompat.recreate
import java.util.*

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        val currentLocale = resources.configuration.locale
//        Log.i("2042", "onCreate: 当前语言: ${currentLocale.language}")

        /*val resources = resources
        val configuration = resources.configuration
        configuration.setLocale(Locale.ENGLISH)
        resources.updateConfiguration(configuration, resources.displayMetrics)*/

        // 修改语言设置

//        val locale = Locale.ENGLISH
//        val configuration = resources.configuration
//        configuration.setLocale(locale)
//        configuration.setLayoutDirection(locale) // 添加此行以支持从右到左的语言布局
//        val updatedContext: Context = createConfigurationContext(configuration)
//        updatedContext.createConfigurationContext(configuration)
//        baseContext.resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)

//        val res: Resources = resources
//        val config: Configuration = res.configuration
//        config.setLocale(Locale.ENGLISH)
//        val context = createConfigurationContext(config)


    }
}