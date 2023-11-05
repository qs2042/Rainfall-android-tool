package com.qing.rainfall_tool.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MyActivity : AppCompatActivity() {
    // 初始化(组件创建时调用)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        // 绑定数据
    }

    // 在组件可见但还未获取用户焦点时调用
    override fun onStart() {
        super.onStart()
        // 准备工作
    }

    // 在组件获取用户焦点并开始与用户交互时调用，此时组件处于活动状态。
    override fun onResume() {
        super.onResume()
        // 主要用于数据刷新, 可以设置为重新进一次就刷新一次
    }

    // 在组件失去用户焦点但仍可见时调用，可以用于保存用户数据或暂停动画等操作。
    override fun onPause() {
        super.onPause()
        // 当前应用切后台的时候(例如: 在应用里接电话)
    }

    // 在组件不再可见时调用，可以执行一些资源释放或停止后台任务的操作。
    override fun onStop() {
        super.onStop()
    }

    // 在组件销毁之前调用，可以进行最终的资源清理和释放。
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}