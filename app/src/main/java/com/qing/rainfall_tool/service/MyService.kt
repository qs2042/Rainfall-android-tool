package com.qing.rainfall_tool.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

// Service 是在后台执行长时间运行操作的组件，不提供用户界面。可以在后台执行网络请求、音乐播放、数据同步等任务。
// Service 可以在应用程序的生命周期之外运行，即使用户切换到其他应用程序或设备处于休眠状态，Service 仍然在后台运行。
class MyService: Service() {
    private val binder = MyBinder()

    // 执行后台任务
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    // 停止任务和资源清理
    override fun onDestroy() {
//        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        // 返回一个 IBinder 对象用于与客户端通信
        return binder
    }

    inner class MyBinder : Binder() {
        // 定义自定义方法供客户端调用
        fun getService(): MyService {
            return this@MyService
        }
    }
}
