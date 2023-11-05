package com.qing.rainfall_tool

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.localbroadcastmanager.content.LocalBroadcastManager

// android.app.Service 和 android.bluetooth.Service有什么区别

// Service会在后台启动一个计时器，每秒递增计数。
// 通过sendCountToActivity()方法，将计数结果发送给Activity组件。
class MainService : Service() {
    private var isRunning = false
    private var count = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()
        isRunning = false
        count = 0
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                if (isRunning) {
                    count++
                    sendCountToActivity(count)
                    handler.postDelayed(this, 1000) // 每秒递增计数
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isRunning = true
        handler.postDelayed(runnable, 1000)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        handler.removeCallbacks(runnable)
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun sendCountToActivity(count: Int) {
        val intent = Intent("COUNT_UPDATED")
        intent.putExtra("count", count)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}