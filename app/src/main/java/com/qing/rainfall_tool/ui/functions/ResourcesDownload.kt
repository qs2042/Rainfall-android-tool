package com.qing.rainfall_tool.ui.functions

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.qing.lib_common.NetworkUtils
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.component.FlowRadioGroup


class ResourcesDownload : AppCompatActivity() {
    // member
    private val TAG = "ResourcesDownload"
    private val LAYOUT = R.layout.act_resources_download

    // member(UI)
    private lateinit var frg: FlowRadioGroup
    private lateinit var download: Button
    private lateinit var url: TextInputEditText

    // member

    private fun init() {
        frg = findViewById(R.id.flow_radio_group)
        download = findViewById(R.id.btn_download)
        url = findViewById(R.id.tv_url)
    }

    private fun executeFRG() {
        // 设置默认选择
        frg.check(R.id.frg_default)

        // 设置变更的监听事件
        /*frg.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<RadioButton>(checkedId)

            when (checkedId) {
                R.id.frg_default -> {

                }
                R.id.frg_bilibili -> {

                }
                else -> {

                }
            }
            Toast.makeText(this, "你选择了: ${rb.text}", Toast.LENGTH_SHORT)
                .show()
        }*/
    }

    private fun executeDownload() {
        download.setOnClickListener{
            // 判断URL是否为空
            if (url.text!!.trim().isEmpty()) {
                Toast.makeText(this, "您还未输入URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (frg.checkedRadioButtonId) {
                R.id.frg_default -> {
                    // 判断URL是否为网站链接
                    if (!NetworkUtils.isWebsiteLink(url.text.toString())) {
                        Toast.makeText(this, "该URL并非是Http or Https", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    // 判断URL是否为磁力链
                    Toast.makeText(this, "正在下载中", Toast.LENGTH_SHORT).show()
                }
                R.id.frg_bilibili -> {
                    // 判断URL是否为av号

                    // 判断URL是否为bv号

                    // 判断URL是否为网站链接 且 是否为B站资源链接
                }
                else -> {
                    Toast.makeText(this, "暂不支持这种方式", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        init()

        executeFRG()
        executeDownload()

    }
}