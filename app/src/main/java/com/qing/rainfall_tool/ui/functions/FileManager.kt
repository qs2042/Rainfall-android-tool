package com.qing.rainfall_tool.ui.functions

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.qing.lib_common.FileUtils
import com.qing.rainfall_tool.R

class FileManager : AppCompatActivity() {
    private lateinit var internalStoragePath: TextView
    private lateinit var totalMemory: TextView
    private lateinit var usedMemory: TextView
    private lateinit var availableMemory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_file_manager)

        internalStoragePath = findViewById(R.id.tv_internal_storage_path)
        totalMemory = findViewById(R.id.tv_total_memory)
        usedMemory = findViewById(R.id.tv_used_memory)
        availableMemory = findViewById(R.id.tv_available_memory)

        val si = FileUtils.getStorageInfo()

        internalStoragePath.text = si.internalStoragePath
        totalMemory.text = si.getTotalMemoryByString()
        usedMemory.text = si.getUsedMemoryByString()
        availableMemory.text = si.getAvailableMemoryByString()
    }
}