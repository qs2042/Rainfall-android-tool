package com.qing.rainfall_tool.ui.functions

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.qing.lib_common.IOUtils
import com.qing.lib_common.NetworkUtils
import com.qing.rainfall_tool.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class IpSearch : AppCompatActivity() {
    private val TAG = this::class.simpleName

    private lateinit var textIp: TextInputEditText
    private lateinit var buttonStart: Button
    private lateinit var showRemote: TextView
    private lateinit var showPing: TextView

    private fun executePing(host: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val process = Runtime.getRuntime().exec("/system/bin/ping -c 4 $host")
            val response = IOUtils.toText(process.inputStream)

            withContext(Dispatchers.Main) {
                showPing.text = response
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_ip_search)

        textIp = findViewById(R.id.text_ip)
        buttonStart = findViewById(R.id.button)
        showRemote = findViewById(R.id.tv_show_remote)
        showPing = findViewById(R.id.tv_show_ping)

        buttonStart.setOnClickListener {
            val ip = textIp.text.toString()

            if (!NetworkUtils.isIPAddress(ip) && !NetworkUtils.isDomainName(ip)) {
                Toast.makeText(this, "IP格式不对", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val job = GlobalScope.launch(Dispatchers.IO) {
                // 在后台执行耗时操作
                val info = NetworkUtils.getIPInfo(textIp.text.toString())
                Log.i(TAG, "onCreate: $info")

                // 在主线程中更新 UI 视图
                withContext(Dispatchers.Main) {
                    showRemote.text = info
                    // job中的this和this@IpSearch应该不是同一个对象
                    Toast.makeText(this@IpSearch, "获取成功", Toast.LENGTH_SHORT).show()
                }
            }

            executePing(ip)
        }
    }
}