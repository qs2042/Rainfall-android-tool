package com.qing.rainfall_tool.ui.functions

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qing.rainfall_tool.R


class WrappedH5 : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_wrapped_h5)
        webView = findViewById(R.id.webView)

        setView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    protected fun setView() {

        val settings = webView.settings
        // 启用JavaScript
        settings.javaScriptEnabled = true

        // 启用自动加载图片
        settings.loadsImagesAutomatically = true

        // 启用缓存, 并设置模式为优先使用缓存, 并设置缓存大小(10MB)
//        settings.setAppCacheEnabled(true)
//        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//        settings.setAppCacheMaxSize((10 * 1024 * 1024).toLong())

        // 启用localStorage
        settings.domStorageEnabled = true

        // 启用读取文件缓存(manifest生效)
        settings.allowFileAccess = true

        settings.pluginState = WebSettings.PluginState.ON

        // 加速WebView加载的方法
        settings.javaScriptCanOpenWindowsAutomatically = true

        // 提高渲染的优先级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)

        // 滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        webView.webViewClient = MyWebViewClient()
        webView.webChromeClient = MyWebChromeClient()
        webView.loadUrl("http://124.223.174.181/bbs/#/")

    }

    internal class MyWebChromeClient : WebChromeClient() {
        // 当网页需要显示一个 JavaScript alert 对话框时被调用
        override fun onJsAlert(
            view: WebView,
            url: String,
            message: String,
            result: JsResult
        ): Boolean {
            // 返回 true 表示已处理该对话框；返回 false 表示继续使用 WebView 默认的处理逻辑
            return false
        }

        // 当页面加载进度发生变化时被调用
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }

        // 当网页标题发生变化时被调用
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            (view.context as? Activity)?.title = title
            (view.context as? Activity)?.title = view.url
        }
    }

    internal class MyWebViewClient : WebViewClient() {
        // 在加载新的网页时被调用，允许开发者拦截网页请求并自行处理
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val myUrl = request.url.toString()

            // 如果加载的是http或者https就返回false在本软件进行打开
            // 如果加装的是自定义协议链接就返回true交给系统浏览器处理
            if (myUrl.startsWith("http://") || myUrl.startsWith("https://") || myUrl.startsWith("www")) {
                view.loadUrl(myUrl)
                return false
            } else {
                Toast.makeText(view.context, myUrl, Toast.LENGTH_SHORT).show()
                Toast.makeText(view.context, "该链接可能需要跳转到相关应用，以进行拦截", Toast.LENGTH_SHORT).show()
            }

            // 返回 true 表示拦截请求，不加载网页
            // 返回 false 表示继续加载网页
            return true
        }

        // 当页面开始加载时被调用
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        // 当页面加载完成时被调用
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            // MyWebView.loadUrl("Javascript:alert('hello world')")
            // MyWebView.evaluateJavascript("")
            // MyWebView.evaluateJavascript("Javascript:alert('hello world')",null)
            // MyWebView.addJavascriptInterface()
        }

        // 当页面加载发生错误时被调用
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
        }
    }
}