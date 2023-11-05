package com.qing.rainfall_tool.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*


// 自定义 LifecycleObserver 类
class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent() {
        // 在组件创建时调用
        // 执行一些初始化操作
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent() {
        // 在组件可见但还未获取用户焦点时调用
        // 执行一些准备工作
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent() {
        // 在组件获取用户焦点并开始与用户交互时调用
        // 执行一些操作
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent() {
        // 在组件失去用户焦点但仍可见时调用
        // 执行一些操作，如保存用户数据或暂停动画等
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent() {
        // 在组件不再可见时调用
        // 执行一些资源释放或停止后台任务的操作
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent() {
        // 在组件销毁之前调用
        // 执行最终的资源清理和释放
    }
}

class TestActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var myObserver: MyObserver
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 创建 LifecycleObserver 对象
        myObserver = MyObserver()

        // 创建 LifecycleRegistry 对象
        lifecycleRegistry = LifecycleRegistry(this)

        // 将 LifecycleObserver 对象添加到 LifecycleRegistry 中
        lifecycleRegistry.addObserver(myObserver)

        // 将 LifecycleObserver 对象添加到 LifecycleOwner 的生命周期观察者列表中
//        lifecycle.addObserver(myObserver)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}