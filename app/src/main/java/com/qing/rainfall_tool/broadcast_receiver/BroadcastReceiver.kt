package com.qing.rainfall_tool.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


// 需要在 AndroidManifest.xml 中声明广播接收器
class MyBroadcastReceiver : BroadcastReceiver() {

    // 广播接收器在接收到广播后会在主线程上执行，因此要注意避免在onReceive()方法中执行耗时操作，以免影响应用的响应性能。
    override fun onReceive(context: Context?, intent: Intent) {
        // abortBroadcast() 可以截断有序广播

        val action = intent.action
        if (action == Intent.ACTION_BOOT_COMPLETED) { // 设备启动
            // 可以启动服务、恢复定时任务等
            Toast.makeText(context, "设备启动", Toast.LENGTH_SHORT).show()
        } else if (action == Intent.ACTION_POWER_CONNECTED) { // 设备连接电源
            // 可以更新UI、播放声音等
            Toast.makeText(context, "设备连接电源", Toast.LENGTH_SHORT).show()
        } else if (action == Intent.ACTION_AIRPLANE_MODE_CHANGED) { // 打开/关闭飞行模式
            Toast.makeText(context, "关闭/打开飞行模式", Toast.LENGTH_SHORT).show()
        } else if (action == Intent.ACTION_SCREEN_ON) { // 设备屏幕打开
            Toast.makeText(context, "屏幕打开", Toast.LENGTH_SHORT).show()
        } else if (action == Intent.ACTION_SCREEN_OFF) { // 设备屏幕关闭
            Toast.makeText(context, "屏幕已关闭", Toast.LENGTH_SHORT).show()
        } else if (action == "com.qing.rainfall_tool.MY_ACTION") {
            Log.i("2042", "onReceive: 自定义的广播")
            Toast.makeText(context, "自定义的广播", Toast.LENGTH_SHORT).show()
        }
    }
}