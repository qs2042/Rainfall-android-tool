package com.qing.rainfall_tool.demo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/*
Fragment的生命周期方法与宿主Activity的生命周期方法有所不同，但它们之间存在一定的关联

例如，当宿主Activity的onPause()方法被调用时，位于该Activity上的Fragment的onPause()方法也会被调用

这种关联可以用于在Fragment与Activity之间进行数据交互和通信。
 */
class MyFragment: Fragment() {
    // 当Fragment与Activity关联时调用
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 在该方法中，Fragment与宿主Activity建立关联，并可以通过getActivity()方法获取宿主Activity的引用。

    }

    // 在Fragment创建时调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 在该方法中，通常进行一些初始化操作，如初始化变量、加载布局等。
    }

    // 在Fragment创建视图时调用。
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        // 在该方法中，应该创建Fragment的用户界面，并返回根视图。而不是使用默认的根视图
    }

    // 当Fragment所关联的Activity的onCreate()方法执行完毕时调用
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 在该方法中，通常进行与Activity交互的操作，如获取宿主Activity的引用、设置监听器等。
    }

    // 当Fragment可见时调用。
    override fun onStart() {
        super.onStart()
        // 在该方法中，可以执行一些需要在Fragment可见时处理的操作。
    }

    // 当Fragment可见且位于栈顶时调用。
    override fun onResume() {
        super.onResume()
        // 在该方法中，可以执行一些与UI交互的操作，如启动动画、注册广播接收器等。
    }

    // 当Fragment即将失去焦点或不再位于栈顶时调用。
    override fun onPause() {
        super.onPause()
        // 在该方法中，应该停止与UI相关的操作，如停止动画、注销广播接收器等。
    }

    // 当Fragment不可见时调用。
    override fun onStop() {
        super.onStop()
        // 在该方法中，可以进行一些与UI无关的资源释放操作。
    }

    // 在Fragment的视图被销毁时调用。
    override fun onDestroyView() {
        super.onDestroyView()
        // 在该方法中，应该释放与视图相关的资源。
    }

    // 当Fragment即将被销毁时调用。
    override fun onDestroy() {
        super.onDestroy()
        // 在该方法中，可以进行一些最终的资源释放操作。
    }

    // 当Fragment与Activity解除关联时调用。
    override fun onDetach() {
        super.onDetach()
        // 在该方法中，Fragment与宿主Activity的关联被解除，并且无法再通过getActivity()方法获取宿主Activity的引用。
    }
}